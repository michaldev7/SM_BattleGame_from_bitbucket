package com.michal.battleship.service.impl;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.GameIDCounter;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.domain.type.UserGameStatus;
import com.michal.battleship.dto.StatusDTO;
import com.michal.battleship.service.MapService;
import org.springframework.stereotype.Service;

@Service
public class MapServiceImpl implements MapService {

    public static final int DEFAULT_SCORE = 0;

    @Override
    public StatusDTO mapToStatusDTO(Game game, PlayerType asking) {
        var hostScore = game.getPlayerA().getScore();
        var guestScore = getGuestScore(game);

        int yourScore = asking.isHost() ? hostScore : guestScore;
        int opponentScore = asking.isHost() ? guestScore : hostScore;
        return new StatusDTO(yourScore, opponentScore, getStatus(game, asking));
    }

    /*
    If game is awaiting players, then (guest/PlayerB) score is not available yet
     */
    private int getGuestScore(Game game) {
        return game.getPlayerB() == null ? GameIDCounter.NEW_GAME_SCORE : game.getPlayerB().getScore();
    }

    /*
    Calculate status of game
     */
    private UserGameStatus getStatus(Game game, PlayerType asking) {
        boolean isGameAwaitingPlayer = game.getPlayerB() == null;
        if (isGameAwaitingPlayer) return UserGameStatus.AWAITING_PLAYERS;

        boolean isWinnerExist = game.getWinner() != null;
        if (isWinnerExist) {
            PlayerType winner = game.getWinner().getPlayerType();
            return asking.isGuest() && winner.isGuest() ? UserGameStatus.YOU_WON : UserGameStatus.YOU_LOST;
        }

        boolean isOpponentTurn = asking.isHost() && game.getPlayerTurn().isGuest();
        return isOpponentTurn ? UserGameStatus.WAITING_FOR_OPPONENT_MOVE : UserGameStatus.YOUR_TURN;
    }
}
