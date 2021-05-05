package com.michal.battleship.domain.type;

public enum Field {

    ONE_DECKER(1, true),
    TWO_DECKER(2, true),
    THREE_DECKER(3, true),
    FOUR_DECKER(4, true),
    SHOOTED_FIELD_BY_PLAYER(10, false),
    MISS_NO_SHIP(20, false);

    public final int shipSize;
    public final boolean isSuccessHit;

    Field(int i, boolean isScorable) {
        shipSize = i;
        isSuccessHit = isScorable;
    }
}
