package com.michal.battleship.service.impl;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.GameBuilder;
import com.michal.battleship.domain.GameIDCounter;
import com.michal.battleship.domain.Player;
import com.michal.battleship.dto.InvitationURLDTO;
import com.michal.battleship.dto.StatusDTO;
import com.michal.battleship.exception.ApiException;
import com.michal.battleship.service.GameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {

    private GameIDCounter gameID = new GameIDCounter();

    private final Map<Long, Game> games = new ConcurrentHashMap<>();

    @Value("${game.invitation.url}")
    private String InvitationURL;

    @Override
    public Game createGame() {
        Game game = new GameBuilder(gameID.incrementAndGet()).setPlayerA(new Player()).build();
        save(game);
        return game;
    }

    @Override
    public synchronized void save(Game game) {
        games.put(game.getId(), game);
    }

    @Override
    public InvitationURLDTO getInvitationURL(long gameId) {
        return new InvitationURLDTO(InvitationURL, gameId);
    }

    @Override
    public Game joinGame(Long id) {
        Game game = games.get(id);
        if(game== null) throw new ApiException(HttpStatus.NOT_FOUND, "Game not exist. Please provide correct game ID or create new game");
        addGuestPlayer(game);
        return game;
    }

    private synchronized void addGuestPlayer(Game game){
        game.setPlayerB(new Player());
        save(game);
    }

    @Override
    public StatusDTO createStatusDTO(long gameId) {
        return null;
    }

}
