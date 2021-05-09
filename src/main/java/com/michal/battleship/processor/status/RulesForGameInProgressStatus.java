package com.michal.battleship.processor.status;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.domain.type.GameStatusDisplay;
import com.michal.battleship.dto.internal.StatusDTO;

import java.util.Optional;

public class RulesForGameInProgressStatus implements StatusRuleCommander {

    private final Game game;
    private final PlayerType asking;

    public RulesForGameInProgressStatus(Game game, PlayerType playerAskingForStatus) {
        this.game = game;
        this.asking = playerAskingForStatus;
    }

    private GameStatusDisplay getStatusIfApplicable() {
        if (game.isGameAwaitingPlayer2() || game.getWinner() != null) return null;
        boolean isTheSamePlayerAskingForStatus = game.getTurn().isGuest() && asking.isGuest();
        return isTheSamePlayerAskingForStatus ? GameStatusDisplay.YOUR_TURN : GameStatusDisplay.WAITING_FOR_OPPONENT_MOVE;
    }

    @Override
    public Optional<StatusDTO> findStatus() {
        GameStatusDisplay status = getStatusIfApplicable();
        if (status != null) {
            return Optional.of(new StatusDTO(status, game.getYourScore(asking), game.getOpponentScore(asking)));
        }
        return Optional.empty();
    }
}
