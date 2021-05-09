package com.michal.battleship.processor.status;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.dto.internal.StatusDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StatusProcessor {

    public static StatusDTO calculateStatus(Game game, PlayerType asking) {
        for (var rule : getStatusRules(game, asking)) {
            Optional<StatusDTO> status = rule.findStatus();
            if (status.isPresent() && status.get().isStatusFound()) {
                return status.get();
            }
        }
        return new StatusDTO();
    }

    private static List<StatusRuleCommander> getStatusRules(Game game, PlayerType asking) {
        return Arrays.asList(
                new RulesForAwaitingPlayers(game),
                new RulesForFinishedGameStatus(game, asking),
                new RulesForGameInProgressStatus(game, asking));
    }

}
