package com.michal.battleship.service;

import com.michal.battleship.domain.Game;

import java.util.Map;

public interface GameService {

    long createGame();

    Game getGameById(long id);

    Map<Long, Game> findAll();

    void save(Game game);




}
