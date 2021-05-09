package com.michal.battleship.domain.type;

public enum ShipType {

    ONE_DECKER("HIT"),
    TWO_DECKER("HIT"),
    THREE_DECKER("HIT"),
    FOUR_DECKER("HIT"),
    POSITION_DESTROYED("MISS"), // position was hit in past by attacker
    POSITION_MISS("MISS"); // position where ship not exist and attack will fail

    private final String onAttackDisplayMessage;

    ShipType(String onAttackDisplayMessage) {
        this.onAttackDisplayMessage = onAttackDisplayMessage;
    }

    public String getResultMessageForPlayer() {
        return onAttackDisplayMessage;
    }

    public boolean isShipFound() {
        return (this == ONE_DECKER) || (this == TWO_DECKER) || (this == THREE_DECKER) || (this == FOUR_DECKER);
    }
}