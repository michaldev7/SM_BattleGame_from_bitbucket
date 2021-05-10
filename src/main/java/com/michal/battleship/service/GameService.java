package com.michal.battleship.service;

import com.michal.battleship.domain.Game;
import com.michal.battleship.dto.internal.StatusDTO;
import com.michal.battleship.dto.internal.HitResultDTO;
import com.michal.battleship.dto.HitRequestDTO;
import com.michal.battleship.dto.InvitationResponseDTO;

public interface GameService {

    Game createGame();

    InvitationResponseDTO getInvitationURL(long gameId);

    Game joinGame(Long id);

    HitResultDTO sendHitRequest(Long id, String token, HitRequestDTO hitDTO);

    /*
    According to status request, application will recognize which player is asking for status, then
    correct status response can be prepared, because each player will have different message
     */
    StatusDTO getStatusFor(long gameId, String token);
}
