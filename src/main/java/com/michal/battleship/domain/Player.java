package com.michal.battleship.domain;

import com.michal.battleship.generic.GameBoard;
import com.michal.battleship.domain.type.PlayerType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Player {

    private PlayerType playerType;

    private BattleBoard battleBoard;

    private String token = UUID.randomUUID().toString();

    /*
    Force to not generate 'GET' method to avoid redundant code
     */
    @Getter(AccessLevel.NONE)
    private AtomicInteger score = new AtomicInteger(0);

    public int getScore() {
        return score.get();
    }

    public void addScore() {
        score.incrementAndGet();
    }

    public Player(PlayerType type) {
        this.playerType = type;
        this.battleBoard = new BattleBoard(new GameBoard().drawBattleBoard(type));
    }
}
