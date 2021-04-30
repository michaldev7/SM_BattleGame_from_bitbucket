package com.michal.battleship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public class Game {

    private long id;
    private Player playerA;
    private Player playerB;

    public Game(long id, Player playerA, Player playerB) {
        this.id=id;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game))
            return false;
        Game other = (Game) o;
        return this.id == other.id;
    }

}
