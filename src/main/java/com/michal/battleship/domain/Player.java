package com.michal.battleship.domain;

import com.michal.battleship.generic.GameBoard;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.generic.GameConfig;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Player {

    private PlayerType playerType;

    private BattleBoard battleBoard;

    private String token = UUID.randomUUID().toString();

    /*
    Not generate 'GET' method to avoid redundant code
     */
    @Getter(AccessLevel.NONE)
    private AtomicInteger score = new AtomicInteger(GameConfig.START_SCORE);

    public int getScore() {
        return score.get();
    }

    public void addScoreAndGet() {
        score.incrementAndGet();
    }

    public Player(PlayerType type) {
        this.playerType = type;
        this.battleBoard = new BattleBoard(new GameBoard().drawBattleBoard(type));
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Player))
            return false;
        Player other = (Player) o;
        return this.token.equals(other.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

    public boolean isMatch(PlayerType playerToCompare) {
        return playerType == playerToCompare;
    }
}
