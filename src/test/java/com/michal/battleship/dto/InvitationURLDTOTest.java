package com.michal.battleship.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InvitationURLDTOTest {

    @Test
    void shouldCreateCorrectInvitation(){
        String gameId = "1";
        InvitationURLDTO dto = new InvitationURLDTO(gameId);
        assertEquals("/game/1/join", dto.getInvitationUrl());
    }

}