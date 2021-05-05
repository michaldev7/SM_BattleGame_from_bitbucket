package com.michal.battleship.domain;

import com.michal.battleship.domain.type.PlayerType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("Each player in game should have one token when Player object is created.")
    void shouldCreatePlayerTokenWhenPlayerIsCreated(){
        Player p = new Player(PlayerType.HOST);
        assertNotNull(p.getToken());
        assertTrue(p.getToken().length()>0);
    }

    @Test
    @DisplayName("When new player is created, then his score of game should be zero.")
    void newCreatedPlayerShouldHaveZeroScore(){
        Player p = new Player(PlayerType.HOST);
        assertNotNull(p.getScore());
        assertEquals(0, p.getScore());
    }
}