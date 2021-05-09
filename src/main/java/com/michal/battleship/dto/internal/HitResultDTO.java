package com.michal.battleship.dto.internal;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.ShipType;
import lombok.AllArgsConstructor;
import lombok.Data;

/*
 * Internal helper DTO to keep attack result in one place
 */
@Data
@AllArgsConstructor
public class HitResultDTO {

    String result;
    Boolean sunken;
    String shipType;
    Game game;
    boolean switchTurn;

    public HitResultDTO(ShipType resultAndShipType, boolean isSunken, Game currentGame) {
        result = resultAndShipType.getResultMessageForPlayer();
        game = currentGame;
        if (isSuccessHit(resultAndShipType)) {
            sunken = isSunken;
            shipType = resultAndShipType.name();
            switchTurn = false;
        } else {
            switchTurn = true;
        }
    }

    private boolean isSuccessHit(ShipType result) {
        return result.isShipFound();
    }
}
