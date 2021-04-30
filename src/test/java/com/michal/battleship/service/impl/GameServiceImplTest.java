package com.michal.battleship.service.impl;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.Player;
import com.michal.battleship.service.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {

    GameService service;

    @BeforeEach
    public void initService() {
        this.service = new GameServiceImpl();
    }

//    @Test
//    void shouldReturnNullIfGameNotFound() {
//        assertNull(service.getGameById(1));
//    }

//    @Test
//    @Disabled
//    void shouldFindNewAddedGameById() {
//        Game game = new Game(1);
//        service.save(game);
//        Game gameById = service.getGameById(game.getId());
//        assertNotNull(gameById);
//    }

    @Test
    void shouldCreateNewIdForEachNewGame() {
        assertNotEquals(service.createGame(), service.createGame());
    }

    @Test
    @DisplayName("New player need to have generated token")
    void newCreatedPlayerShouldHaveGeneratedToken() {
        assertNotNull(new Player().getToken());
    }

}