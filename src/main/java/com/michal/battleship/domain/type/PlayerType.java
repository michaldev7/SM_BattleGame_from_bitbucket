package com.michal.battleship.domain.type;

/*
 * Player type
 *
 * HOST - is a player who create the game
 * GUEST - is a second player who joined to any existing game
 * @return false when ship is not sunken
 */
public enum PlayerType {

    GUEST,
    HOST;

    public boolean isGuest() {
        return PlayerType.GUEST == this;
    }

    public boolean isHost() {
        return PlayerType.HOST == this;
    }

}