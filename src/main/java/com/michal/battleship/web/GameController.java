package com.michal.battleship.web;

import com.michal.battleship.domain.Game;
import com.michal.battleship.dto.*;
import com.michal.battleship.dto.internal.StatusDTO;
import com.michal.battleship.dto.internal.HitResultDTO;
import com.michal.battleship.mapper.Converter;
import com.michal.battleship.exception.ApiException;
import com.michal.battleship.generic.GameConfig;
import com.michal.battleship.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.michal.battleship.domain.type.PlayerType.GUEST;
import static com.michal.battleship.domain.type.PlayerType.HOST;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private Converter converter;

    @PostMapping
    public ResponseEntity<InvitationResponseDTO> create() {
        Game game = gameService.createGame();
        InvitationResponseDTO invitation = gameService.getInvitationURL(game.getId());
        return ResponseEntity.ok().headers(game.getTokenHeaders(HOST)).body(invitation);
    }

    @PostMapping("/{id}/join")
    public ResponseEntity<StatusResponseDTO> join(@PathVariable Long id) {
        Game game = gameService.joinGame(id);
        StatusDTO statusFor = gameService.getStatusFor(id, game.getPlayerB().getToken());
        StatusResponseDTO convert = converter.convert(statusFor);
        return ResponseEntity.ok().headers(game.getTokenHeaders(GUEST)).body(convert);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HitResponseDTO> hit(@PathVariable Long id, @RequestBody @Valid HitRequestDTO hitDTO,
                                              @RequestHeader(value = GameConfig.TOKEN_KEY) String token, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, binding);
        }
        HitResultDTO attackReport = gameService.sendHitRequest(id, token, hitDTO);
        HitResponseDTO response = converter.convert(attackReport);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusResponseDTO> getStatus(@PathVariable Long id, @RequestHeader(value = GameConfig.TOKEN_KEY) String token) {
        StatusDTO status = gameService.getStatusFor(id, token);
        StatusResponseDTO response = converter.convert(status);
        return ResponseEntity.ok().body(response);
    }
}
