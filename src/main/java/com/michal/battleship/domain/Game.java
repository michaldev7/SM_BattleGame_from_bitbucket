package com.michal.battleship.domain;

import com.michal.battleship.domain.type.ShipType;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.dto.HitRequestDTO;
import com.michal.battleship.exception.ApiException;
import com.michal.battleship.generic.GameConfig;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@Data
public class Game {

    private long id;
    private Player playerA;
    private Player playerB;
    private boolean locked;
    private PlayerType turn;
    private Player winner = null;

    public Game(long id, Player playerA) {
        this.id = id;
        this.playerA = playerA;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game))
            return false;
        Game other = (Game) o;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /*
    Helper methods for keep token logic inside Game class instead of controllers.
     */
    public HttpHeaders getTokenHeaders(PlayerType type) {
        var headers = new HttpHeaders();
        headers.set(GameConfig.SET_TOKEN_KEY, type.isHost() ? getPlayerA().getToken() : getPlayerB().getToken());
        return headers;
    }

    public Player findPlayerByTokenThrowable(String token) {
        Optional<Player> player = Stream.of(playerB, playerA).filter(Objects::nonNull).filter(p -> p.getToken().equals(token)).findAny();
        return player.orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "You don't have access for this game. Please check token or create new game"));
    }

    public void isNowTurnFor(Player player) {
        if (player.getPlayerType() != turn)
            throw new ApiException(HttpStatus.METHOD_NOT_ALLOWED, "Please wait for your turn. Awaiting opponent move.");
    }

    public Player getAwaitingOpponent() {
        return turn.isGuest() ? playerB : playerA;
    }

    public void destroyShipAt(HitRequestDTO hit) {
        getAwaitingOpponent().getBattleBoard().getGameBoard().put(hit.getPosition(), ShipType.POSITION_DESTROYED);
    }

    /*
    This method return what opponent has placed at given position at game board
     */
    public ShipType getOpponentPositionAt(HitRequestDTO hit) {
        return getAwaitingOpponent().getBattleBoard().getGameBoard().get(hit.getPosition());
    }

    public void isFreeSlotAvailableThrowable() {
        if (getPlayerB() != null)
            throw new ApiException(HttpStatus.FORBIDDEN, "This operation is not allowed, because game was started earlier.");
    }

    public void addPlayer() {
        playerB = new Player(PlayerType.GUEST);
        turn = PlayerType.GUEST;
    }

    /*
    Change turn to opponent move
    and setup first turn for new joiner (guest)
     */
    public void switchTurn() {
        turn = turn.isHost() ? PlayerType.GUEST : PlayerType.HOST;
    }

    public boolean isGameAwaitingPlayer2() {
        return getPlayerB() == null;
    }

    public void setWinnerIfMaxScoreAchieved() {
        if (getPlayerB().getScore() == GameConfig.GAME_SIZE) this.winner = playerB;
        else if (getPlayerA().getScore() == GameConfig.GAME_SIZE) this.winner = playerA;
    }

    public int getYourScore(PlayerType type) {
        return type.isGuest() ? playerB.getScore() : playerA.getScore();
    }

    public int getOpponentScore(PlayerType type) {
        return type.isHost() ? playerB.getScore() : playerA.getScore();
    }

    /*
    Each player have the same size of board so we can compare only one board
    to check if position is out of game board map.
     */
    public boolean isPositionOutOfGameBoard(String position) {
        return !getPlayerA().getBattleBoard().getGameBoard().containsKey(position);
    }

}
