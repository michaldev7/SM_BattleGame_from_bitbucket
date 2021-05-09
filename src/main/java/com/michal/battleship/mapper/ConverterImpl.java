package com.michal.battleship.mapper;

import com.michal.battleship.dto.internal.StatusDTO;
import com.michal.battleship.dto.internal.HitResultDTO;
import com.michal.battleship.dto.StatusResponseDTO;
import com.michal.battleship.dto.HitResponseDTO;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.stereotype.Service;

@Service
public class ConverterImpl implements Converter {

    private final MapperFacade mapper;

    public ConverterImpl() {
        MapperFactory factory = new DefaultMapperFactory
                .Builder().build();

        factory.classMap(HitResponseDTO.class, HitResultDTO.class)
                .field("result", "result")
                .field("sunken", "sunken")
                .field("shipType", "shipType")
                .byDefault()
                .register();

        factory.classMap(StatusResponseDTO.class, StatusDTO.class)
                .field("gameStatus", "gameStatusDisplay")
                .field("yourScore", "yourScore")
                .field("opponentScore", "opponentScore")
                .byDefault()
                .register();

        factory.classMap(StatusDTO.class, StatusResponseDTO.class)
                .field("gameStatusDisplay", "gameStatus")
                .field("yourScore", "yourScore")
                .field("opponentScore", "opponentScore")
                .byDefault()
                .register();
        mapper = factory.getMapperFacade();
    }

    @Override
    public HitResponseDTO convert(HitResultDTO sourceOrder) {
        return mapper.map(sourceOrder, HitResponseDTO.class);
    }

    @Override
    public StatusResponseDTO convert(StatusDTO statusFor) {
        return mapper.map(statusFor, StatusResponseDTO.class);
    }
}