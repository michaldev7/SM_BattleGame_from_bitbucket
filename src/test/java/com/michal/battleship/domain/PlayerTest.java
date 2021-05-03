package com.michal.battleship.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    @DisplayName("Each player in game should have one token when Player object is created.")
    void shouldCreatePlayerTokenWhenPlayerIsCreated(){
        Player p = new Player();
        assertNotNull(p.getToken());
        assertTrue(p.getToken().length()>0);
    }

    @Test
    @DisplayName("When new player is created, then his score of game should be zero.")
    void newCreatedPlayerShouldHaveZeroScore(){
        Player p = new Player();
        assertNotNull(p.getScore());
        assertEquals(0, p.getScore());
    }
}