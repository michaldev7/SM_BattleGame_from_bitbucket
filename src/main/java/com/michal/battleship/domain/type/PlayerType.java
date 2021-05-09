package com.michal.battleship.domain.type;

public enum PlayerType {

    GUEST,
    HOST;

    public boolean isGuest() {
        return  PlayerType.GUEST == this;
    }

    public boolean isHost() {
        return  PlayerType.HOST == this;
    }

}