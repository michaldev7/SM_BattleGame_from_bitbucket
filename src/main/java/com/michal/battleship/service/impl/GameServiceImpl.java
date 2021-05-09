package com.michal.battleship.service.impl;

import com.michal.battleship.domain.*;
import com.michal.battleship.domain.type.ShipType;
import com.michal.battleship.dto.internal.StatusDTO;
import com.michal.battleship.dto.internal.HitResultDTO;
import com.michal.battleship.dto.HitRequestDTO;
import com.michal.battleship.dto.InvitationResponseDTO;
import com.michal.battleship.exception.ApiException;
import com.michal.battleship.processor.status.*;
import com.michal.battleship.service.GameService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class GameServiceImpl implements GameService {

    private final GameIDCounter gameID = new GameIDCounter();

    private final Map<Long, Game> games = new ConcurrentHashMap<>();

    @Value("${game.invitation.url}")
    private String invitationURL;

    @Override
    public Game createGame() {
        var game = new GameBuilder(gameID.incrementAndGet()).buildNewGame();
        return saveAndGet(game);
    }

    private Game addSecondPlayerToGame(Long gameId) {
        var game = findGameById(gameId);
        game.addPlayer();
        saveAndGet(game);
        return game;
    }

    @Override
    public Game joinGame(Long gameId) {
        validateJoinGameRequest(gameId);
        return addSecondPlayerToGame(gameId);
    }

    private Game saveAndGet(Game game) {
        games.put(game.getId(), game);
        return game;
    }

    @Override
    public InvitationResponseDTO getInvitationURL(long gameId) {
        return new InvitationResponseDTO(invitationURL, gameId);
    }

    private void validateJoinGameRequest(Long id) {
        findGameById(id).isFreeSlotAvailableThrowable();
    }

    private Game findGameById(long id) {
        var game = games.get(id);
        if (game == null) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Game not exist. Please provide correct game ID or create new game");
        }
        return game;
    }

    /*
    Lock game to prevent forcing game request
     */
    private synchronized void lock(Game game) {
        game.setLocked(true);
        saveAndGet(game);
    }

    private void unlock(Game game) {
        game.setLocked(false);
        saveAndGet(game);
    }

    @Override
    public HitResultDTO sendHitRequest(Long gameId, String token, HitRequestDTO hitDTO) {
        validateHitRequestAndInitSafetyPreparer(gameId, token, hitDTO);
        HitResultDTO hitResult = executeAttack(gameId, hitDTO);
        addScoreAndSwitchTurnIfNeeded(hitResult, token);
        return hitResult;
    }

    @Override
    public StatusDTO getStatusFor(long gameId, String token) {
        var game = findGameById(gameId);
        var player = validateStatusRequest(gameId, token);
        return StatusProcessor.calculateStatus(game, player.getPlayerType());
    }

    private void addScoreAndSwitchTurnIfNeeded(HitResultDTO attackReport, String token) {
        if (attackReport.isSwitchTurn()) {
            attackReport.getGame().switchTurn();
        } else {
            attackReport.getGame().findPlayerByTokenThrowable(token).addScoreAndGet();
            attackReport.getGame().setWinnerIfMaxScoreAchieved();
        }
        saveAndGet(attackReport.getGame());
    }


    private HitResultDTO executeAttack(Long gameId, HitRequestDTO hit) {
        var game = findGameById(gameId);
        var hitSourceDTO = doAttackGetReport(game, hit);
        unlock(game);
        return hitSourceDTO;
    }

    private boolean isSunken(Game game, ShipType shipType) {
        return game.getAwaitingOpponent().getBattleBoard().isShipSunken(shipType);
    }

    private ShipType getContentOfPosition(Game game, HitRequestDTO hit) {
        return game.getOpponentPositionAt(hit);
    }

    private HitResultDTO doAttackGetReport(Game game, HitRequestDTO hit) {
        var shipType = getContentOfPosition(game, hit);
        game.destroyShipAt(hit);
        return new HitResultDTO(shipType, isSunken(game, shipType), game);
    }

    private void validateHitRequestAndInitSafetyPreparer(Long gameId, String token, HitRequestDTO hit) {
        var game = findGameById(gameId);
        var player = game.findPlayerByTokenThrowable(token);
        StatusDTO status = StatusProcessor.calculateStatus(game, player.getPlayerType());
        if (status.getGameStatusDisplay().isGameCompleted()) {
            throw new ApiException(HttpStatus.METHOD_NOT_ALLOWED, "You cannot send hit request because game is finished");
        }
        boolean isHitPositionOutOfGameMap = !player.getBattleBoard().isPositionInsideGameBoard(hit.getPosition());
        if (isHitPositionOutOfGameMap) {
            throw new ApiException(HttpStatus.NOT_ACCEPTABLE, "Position from request is incorrect and out of game map range. Allowed positions are between A1-J10");
        }
        if (game.isLocked()) {
            throw new ApiException(HttpStatus.FORBIDDEN, "Detected force multiple request. You are only allowed to send one position to hit at the same time. Try again if it's your turn.");
        }
        game.isNowTurnFor(player);
        lock(game);
    }

    private Player validateStatusRequest(long gameId, String token) {
        var game = findGameById(gameId);
        return game.findPlayerByTokenThrowable(token);
    }
}
