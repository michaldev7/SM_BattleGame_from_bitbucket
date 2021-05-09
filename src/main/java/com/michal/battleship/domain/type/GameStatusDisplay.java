package com.michal.battleship.domain.type;

public enum GameStatusDisplay {

    AWAITING_PLAYERS(),
    WAITING_FOR_OPPONENT_MOVE(),
    YOUR_TURN(),
    YOU_LOST(),
    YOU_WON();

    public boolean isGameCompleted() {
        return this == YOU_WON || this == YOU_LOST;
    }

}