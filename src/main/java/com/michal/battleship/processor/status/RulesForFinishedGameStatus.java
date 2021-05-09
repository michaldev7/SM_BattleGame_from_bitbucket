package com.michal.battleship.processor.status;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.domain.type.GameStatusDisplay;
import com.michal.battleship.dto.internal.StatusDTO;

import java.util.Optional;

import static com.michal.battleship.domain.type.GameStatusDisplay.*;

public class RulesForFinishedGameStatus implements StatusRuleCommander {

    private final Game game;
    private final PlayerType player;
    private GameStatusDisplay status;

    public RulesForFinishedGameStatus(Game game, PlayerType playerAskingForStatus) {
        this.game = game;
        this.player = playerAskingForStatus;
    }

    private GameStatusDisplay getStatusIfApplicable() {
        GameStatusDisplay status = null;
        boolean hasWinner = game.getWinner() != null;
        if (hasWinner) {
            status = game.getWinner().isMatch(player) ? YOU_WON : YOU_LOST;
        }
        return status;
    }

    @Override
    public Optional<StatusDTO> findStatus() {
        GameStatusDisplay result = getStatusIfApplicable();
        if (result != null) {
            return Optional.of(new StatusDTO(result, game.getYourScore(player), game.getOpponentScore(player)));
        }
        return Optional.empty();
    }
}
