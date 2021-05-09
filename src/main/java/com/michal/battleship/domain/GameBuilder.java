package com.michal.battleship.domain;


import com.michal.battleship.domain.type.PlayerType;

public class GameBuilder {

    private final long id;

    public GameBuilder(long id) {
        this.id = id;
    }

    public Game buildNewGame() {
        var playerTypeOnNewGame = PlayerType.HOST;
        var playerA = new Player(playerTypeOnNewGame);
        return new Game(id, playerA);
    }
}
