package com.michal.battleship.service;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.dto.StatusDTO;

public interface MapService {

    StatusDTO mapToStatusDTO(Game game, PlayerType askingPlayer);
}
