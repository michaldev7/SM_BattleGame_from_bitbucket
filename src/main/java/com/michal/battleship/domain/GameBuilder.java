package com.michal.battleship.domain;


import com.michal.battleship.domain.type.PlayerType;

public class GameBuilder {

    private final long id;
    private Player playerA;
    private Player playerB;

    public GameBuilder(long id) {
        this.id = id;
    }

    public GameBuilder setPlayerA(Player playerA) {
        this.playerA = playerA;
        return this;
    }

    public Game build() {
        playerA.setPlayerType(PlayerType.HOST);
        return new Game(id,playerA, playerB);
    }
}
