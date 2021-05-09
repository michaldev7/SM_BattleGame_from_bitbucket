package com.michal.battleship.processor.status;

import com.michal.battleship.dto.internal.StatusDTO;

import java.util.Optional;

public interface StatusRuleCommander {

    Optional<StatusDTO> findStatus();
}
