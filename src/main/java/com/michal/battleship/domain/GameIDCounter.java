package com.michal.battleship.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class GameIDCounter {

    private final AtomicInteger counter = new AtomicInteger();

    public int incrementAndGet() {
        return  counter.incrementAndGet();
    }

    public int getCounter() {
        return counter.get();
    }
}
