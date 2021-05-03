package com.michal.battleship.service;

import com.michal.battleship.domain.Game;
import com.michal.battleship.dto.InvitationURLDTO;

public interface GameService {

    Game createGame();

    void save(Game game);

    InvitationURLDTO getInvitationURL(long gameId);

    Game joinGame(Long id);

    Game unlock(Game game);
}
