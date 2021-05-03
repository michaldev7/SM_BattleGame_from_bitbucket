package com.michal.battleship.domain.type;

public enum PlayerType {

    GUEST,
    HOST;

    private PlayerType type;

    public boolean isGuest() {
        return  PlayerType.GUEST == this;
    }

    public boolean isHost() {
        return  PlayerType.HOST == this;
    }

    private PlayerType getType() {
        return this.type;
    }
}
