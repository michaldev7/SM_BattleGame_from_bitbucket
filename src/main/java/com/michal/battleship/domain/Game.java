package com.michal.battleship.domain;

import com.michal.battleship.domain.type.PlayerType;
import lombok.Data;
import org.springframework.http.HttpHeaders;

import java.time.Instant;

@Data
public class Game {
    public static final String KEY_TOKEN = "Set-Auth-Token";
    private long id;
    private Player playerA;
    private Player playerB;
    private Instant lockTimestamp;
    private PlayerType playerTurn;
    private Player winner = null;

    public Game(long id, Player playerA, Player playerB) {
        this.id = id;
        this.playerA = playerA;
        this.playerB = playerB;
    }

    /*
    Return true if game.id are equals
     */
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Game))
            return false;
        Game other = (Game) o;
        return this.id == other.id;
    }

    /*
    Helper methods for keep token logic inside Game class instead of controllers.
     */
    public HttpHeaders getTokenHeaders(PlayerType type) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(KEY_TOKEN, type.isHost() ? getPlayerA().getToken() : getPlayerB().getToken());
        return headers;
    }
}
