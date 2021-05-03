package com.michal.battleship.service.impl;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.GameBuilder;
import com.michal.battleship.domain.GameIDCounter;
import com.michal.battleship.domain.Player;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.dto.InvitationURLDTO;
import com.michal.battleship.exception.ApiException;
import com.michal.battleship.service.GameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
        Game game = getIfFreeSlotExistOrThrow(id);
        executeNewPlayerAdding(game);
        return game;
    }

    private void setTurn(Game game, PlayerType playerType) {
        game.setPlayerTurn(playerType);
        save(game);
    }


    /*
    Return Game if game has free slots.

    Return game when:
    1. Game with given id exist
    2. game is awaiting a player
     */
    private Game getIfFreeSlotExistOrThrow(Long id) {
        Game game = findByIdOrThrow(id);
        if (game.getPlayerB() != null)
            throw new ApiException(HttpStatus.FORBIDDEN, "You can't join this game, there is no free slot. Please use another invitation or create new game");
        return game;
    }

    private Game findByIdOrThrow(long id) {
        Game game = games.get(id);
        if (game == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Game not exist. Please provide correct game ID or create new game");
        }
        return game;
    }


    private synchronized Game lockAndGet(Game game) {
        game.setLockTimestamp(Instant.now());
        save(game);
        return game;
    }

//    @Override
//    public Game unlock(long id) {
//        Game game = games.get(id);
//        if (game == null)
//            throw new ApiException(HttpStatus.NOT_FOUND, "Game not exist. Please provide correct game ID or create new game");
//        game.setLockTimestamp(null);
//        save(game);
//        return game;
//    }

    @Override
    public Game unlock(Game game) {
        game.setLockTimestamp(null);
        save(game);
        return game;
    }

    private synchronized Game executeNewPlayerAdding(Game game) {
        lockAndGet(game);
        game.setPlayerB(new Player());
        game.setPlayerTurn(PlayerType.GUEST);
        save(game);
        unlock(game);
        return game;
    }

}
