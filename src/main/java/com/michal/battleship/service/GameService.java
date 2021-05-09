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

    StatusDTO getStatusFor(long gameId, String token);
}
