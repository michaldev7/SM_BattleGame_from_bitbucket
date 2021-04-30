package com.michal.battleship.domain;

import lombok.Data;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Player {

    private String token = UUID.randomUUID().toString();

    private AtomicInteger score = new AtomicInteger(0);

    public void addScore() {
        score.incrementAndGet();
    }
}
