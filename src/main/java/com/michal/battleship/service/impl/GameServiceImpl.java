package com.michal.battleship.service.impl;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.GameIDCounter;
import com.michal.battleship.service.GameService;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {

    private GameIDCounter idCreator = new GameIDCounter();

    private final Map<Long, Game> games = new ConcurrentHashMap<>();

    @Override
    public long createGame() {
        Game game = new Game(idCreator.incrementAndGet());
        save(game);
        return game.getId();
    }

    @Override
    public Game getGameById(long id) {
        return games.get(id);
    }

    @Override
    public void save(Game game) {
        games.put(game.getId(), game);
    }

    @Override
    public Map<Long, Game> findAll() {
        return games;
    }
}
