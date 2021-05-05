package com.michal.battleship.processor;

import com.michal.battleship.domain.type.Field;
import com.michal.battleship.domain.type.PlayerType;

import java.util.HashMap;
import java.util.Map;

public class DefaultBoard {

    private static final Map<String, Field> BOARD_HOST = new HashMap<>(Map.of(
            "A1", Field.ONE_DECKER,
            "D1", Field.TWO_DECKER,
            "D2", Field.TWO_DECKER,
            "G1", Field.THREE_DECKER,
            "G2", Field.THREE_DECKER,
            "G3", Field.THREE_DECKER,
            "J1", Field.FOUR_DECKER,
            "J2", Field.FOUR_DECKER,
            "J2", Field.FOUR_DECKER,
            "J4", Field.FOUR_DECKER
    ));

    private static final Map<String, Field> BOARD_GUEST = new HashMap<>(Map.of(
            "A1", Field.ONE_DECKER,
            "D1", Field.TWO_DECKER,
            "D2", Field.TWO_DECKER,
            "G1", Field.THREE_DECKER,
            "G2", Field.THREE_DECKER,
            "G3", Field.THREE_DECKER,
            "J1", Field.FOUR_DECKER,
            "J2", Field.FOUR_DECKER,
            "J3", Field.FOUR_DECKER,
            "J4", Field.FOUR_DECKER
    ));

    public static Map<String, Field> findByType(PlayerType type) {
        return type.isGuest() ? BOARD_GUEST : BOARD_HOST;
    }
}
