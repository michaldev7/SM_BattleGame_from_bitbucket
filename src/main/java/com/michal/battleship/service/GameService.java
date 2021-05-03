package com.michal.battleship.service;

import com.michal.battleship.domain.Game;
import com.michal.battleship.dto.InvitationURLDTO;
import com.michal.battleship.dto.StatusDTO;
import org.springframework.http.HttpHeaders;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface GameService {

    Game createGame();

    void save(Game game);

    InvitationURLDTO getInvitationURL(long gameId);

    StatusDTO createStatusDTO(long gameId);

    Game joinGame(Long id);
}
