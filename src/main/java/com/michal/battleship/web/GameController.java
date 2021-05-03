package com.michal.battleship.web;

import com.michal.battleship.domain.Game;
import com.michal.battleship.domain.type.PlayerType;
import com.michal.battleship.dto.InvitationURLDTO;
import com.michal.battleship.dto.StatusDTO;
import com.michal.battleship.service.GameService;
import com.michal.battleship.service.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.michal.battleship.domain.type.PlayerType.HOST;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private MapService mapService;

    @PostMapping
    ResponseEntity<InvitationURLDTO> create() {
        Game game = gameService.createGame();
        InvitationURLDTO invitation = gameService.getInvitationURL(game.getId());
        return ResponseEntity.ok().headers(game.getHeaderFor(HOST)).body(invitation);
    }

    @PostMapping("/{id}/join")
    ResponseEntity<StatusDTO> join(@PathVariable Long id) {
        Game game = gameService.joinGame(id);
        PlayerType player = PlayerType.GUEST;
        StatusDTO statusDTO = mapService.mapToStatusDTO(game, player);
        return ResponseEntity.ok().headers(game.getHeaderFor(player)).body(statusDTO);
    }
}
