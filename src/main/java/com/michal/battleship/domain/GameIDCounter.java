package com.michal.battleship.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class GameIDCounter {

    public static final int NEW_GAME_SCORE = 0;

    private final AtomicInteger counter = new AtomicInteger(NEW_GAME_SCORE);

    public int incrementAndGet() {
        return counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}
