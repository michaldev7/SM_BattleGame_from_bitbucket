package com.michal.battleship.web;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.dto.HitDTO;
import com.michal.battleship.dto.HitRequestDTO;
import com.michal.battleship.dto.InvitationURLDTO;
import com.michal.battleship.dto.StatusDTO;
import com.michal.battleship.exception.ApiException;
import com.michal.battleship.service.GameService;
import com.michal.battleship.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.michal.battleship.domain.type.PlayerType.HOST;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private MapService mapService;

    @PostMapping
    private ResponseEntity<InvitationURLDTO> create() {
        Game game = gameService.createGame();
        InvitationURLDTO invitation = gameService.getInvitationURL(game.getId());
        return ResponseEntity.ok().headers(game.getTokenHeaders(HOST)).body(invitation);
    }

    @PostMapping("/{id}/join")
    private ResponseEntity<StatusDTO> join(@PathVariable Long id) {
        Game game = gameService.joinGame(id);
        PlayerType player = PlayerType.GUEST;
        StatusDTO statusDTO = mapService.mapToStatusDTO(game, player);
        return ResponseEntity.ok().headers(game.getTokenHeaders(player)).body(statusDTO);
    }

    @PutMapping("/{id}")
    private ResponseEntity<HitDTO> hit(@PathVariable Long id, @RequestBody @Valid HitRequestDTO request, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, binding);
        }
        return ResponseEntity.ok().body(new HitDTO());
    }
}
