package com.michal.battleship.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;

@Data
@AllArgsConstructor
public class Game {
    private long id;
    private Player playerA;
    private Player playerB;

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
    Return auth token for Player.
     */
    public HttpHeaders getToken() {
        HttpHeaders headers = new HttpHeaders();
        String token = getPlayerB()==null ? getPlayerA().getToken() : getPlayerB().getToken();
        headers.set("Set-Auth-Token", token);
        return headers;
    }
}
