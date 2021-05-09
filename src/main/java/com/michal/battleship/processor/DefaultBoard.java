package com.michal.battleship.processor;

import com.michal.battleship.domain.type.ShipType;
import com.michal.battleship.domain.type.PlayerType;

import java.util.HashMap;
import java.util.Map;

public class DefaultBoard {

    private static final Map<String, ShipType> BOARD_HOST = new HashMap<>(Map.of(
            "A1", ShipType.ONE_DECKER,
            "D1", ShipType.TWO_DECKER,
            "D2", ShipType.TWO_DECKER,
            "G1", ShipType.THREE_DECKER,
            "G2", ShipType.THREE_DECKER,
            "G3", ShipType.THREE_DECKER,
            "J1", ShipType.FOUR_DECKER,
            "J2", ShipType.FOUR_DECKER,
            "J3", ShipType.FOUR_DECKER,
            "J4", ShipType.FOUR_DECKER
    ));

    private static final Map<String, ShipType> BOARD_GUEST = new HashMap<>(Map.of(
            "A1", ShipType.ONE_DECKER,
            "D1", ShipType.TWO_DECKER,
            "D2", ShipType.TWO_DECKER,
            "G1", ShipType.THREE_DECKER,
            "G2", ShipType.THREE_DECKER,
            "G3", ShipType.THREE_DECKER,
            "J1", ShipType.FOUR_DECKER,
            "J2", ShipType.FOUR_DECKER,
            "J3", ShipType.FOUR_DECKER,
            "J4", ShipType.FOUR_DECKER
    ));

    public static Map<String, ShipType> findByType(PlayerType type) {
        return type.isGuest() ? BOARD_GUEST : BOARD_HOST;
    }
}
