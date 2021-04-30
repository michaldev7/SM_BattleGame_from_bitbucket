package com.michal.battleship.domain;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Player {

    AtomicInteger score = new AtomicInteger(0);

    public void addScore(){
        score.incrementAndGet();
    }
}
