package com.michal.battleship.processor.status;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.GameStatusDisplay;
import com.michal.battleship.dto.internal.StatusDTO;
import com.michal.battleship.generic.GameConfig;

import java.util.Optional;

import static com.michal.battleship.domain.type.GameStatusDisplay.AWAITING_PLAYERS;

public class RulesForAwaitingPlayers implements StatusRuleCommander {

    private final Game game;

    public RulesForAwaitingPlayers(Game game) {
        this.game = game;
    }


    private GameStatusDisplay getStatusIfApplicable() {
        if (game.isGameAwaitingPlayer()) return AWAITING_PLAYERS;
        return null;
    }

    @Override
    public Optional<StatusDTO> findStatus() {
        GameStatusDisplay status = getStatusIfApplicable();
        if (status != null) {
            return Optional.of(new StatusDTO(status, GameConfig.START_SCORE, GameConfig.START_SCORE));
        }
        return Optional.empty();
    }
}
