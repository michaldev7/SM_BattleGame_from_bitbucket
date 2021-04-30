package com.michal.battleship.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldReturnTrueWhenEqualsTheSameId() {
        Game game = new Game(1);
        Game game2 = new Game(1);
        assertTrue(game.equals(game2));
    }

    @Test
    void shouldReturnFalseWhenEqualsGamesWithDifferentId() {
        Game game = new Game(1);
        Game game2 = new Game(2);
        assertFalse(game.equals(game2));
    }
}