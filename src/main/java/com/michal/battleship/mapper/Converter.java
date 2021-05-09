package com.michal.battleship.mapper;

import com.michal.battleship.dto.internal.StatusDTO;
import com.michal.battleship.dto.internal.HitResultDTO;
import com.michal.battleship.dto.StatusResponseDTO;
import com.michal.battleship.dto.HitResponseDTO;

public interface Converter {

    HitResponseDTO convert(HitResultDTO sourceCode);

    StatusResponseDTO convert(StatusDTO statusFor);
}
