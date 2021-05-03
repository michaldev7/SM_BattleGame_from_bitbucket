package com.michal.battleship.web;

import com.michal.battleship.domain.Game;
import com.michal.battleship.dto.InvitationURLDTO;
import com.michal.battleship.dto.StatusDTO;
import com.michal.battleship.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private GameService gameService;

    @PostMapping
    ResponseEntity<InvitationURLDTO> createGame() {
        Game game = gameService.createGame();
        InvitationURLDTO invitation = gameService.getInvitationURL(game.getId());
        return ResponseEntity.ok().headers(game.getToken()).body(invitation);
    }

    @PostMapping("/{id}/join")
    ResponseEntity<StatusDTO> joinGame(@PathVariable Long id) {
        Game game = gameService.joinGame(id);
        return ResponseEntity.ok().headers(game.getToken()).body(new StatusDTO());
    }
}
