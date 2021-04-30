package com.michal.battleship.domain;

public class GameBuilder {

    private final long id;
    private Player playerA;
    private Player playerB;

    public GameBuilder(long id, Player playerA, Player playerB) {
        this.id = id;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public GameBuilder(long id) {
        this.id = id;
    }

    public GameBuilder setPlayerA(Player playerA) {
        this.playerA = playerA;
        return this;
    }
    public GameBuilder setPlayerB(Player playerB) {
        this.playerB = playerB;
        return this;
    }

    public Game build() {
        return new Game(id,playerA, playerB);
    }
}
