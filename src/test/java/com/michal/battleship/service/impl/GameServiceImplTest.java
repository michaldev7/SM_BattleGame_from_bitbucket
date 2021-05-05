package com.michal.battleship.service.impl;

import com.michal.battleship.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

class GameServiceImplTest {

    GameService service;

    @BeforeEach
    public void initService() {
        this.service = new GameServiceImpl();
    }

    @DisplayName("each new game should have unique id")
    @Test
    void shouldCreateNewIdForEachNewGame() {
        assertNotEquals(service.createGame(), service.createGame());
    }
}