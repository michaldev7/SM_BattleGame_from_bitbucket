package com.michal.battleship.domain;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void newPlayerScoreShouldBeZero() {
        List list = new ArrayList();
        list.add("st");
        list.add(1);
        assertEquals(0, new Player().getScore().get());
    }

    @Test
    void playerScoreShouldBeIncrementByOnePoint() {
        Player p = new Player();
        p.addScore();
        assertEquals(1, p.getScore().get());
    }
}