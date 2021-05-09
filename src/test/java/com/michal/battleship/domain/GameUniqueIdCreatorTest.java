package com.michal.battleship.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

class GameUniqueIdCreatorTest {

    @Test
    @DisplayName("Should generate game id without any error")
    void shouldGenerateCorrectId() {
        GameIDCounter counter = new GameIDCounter();
        for (int i = 0; i < 500; i++) {
            counter.incrementAndGet();
        }
        assertEquals(500, counter.getCounter());
    }

    @Test
    @DisplayName("Game id generator should calculate correct game ID in multi thread processing")
    void shouldGenerateCorrectIdWhileMultithreadingConcurrentAccess() throws InterruptedException {
        int totalThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(50);
        CountDownLatch latch = new CountDownLatch(totalThreads);
        GameIDCounter counter = new GameIDCounter();
        IntStream.range(0, totalThreads).<Runnable>mapToObj(i -> () -> {
            counter.incrementAndGet();
            latch.countDown();
        }).forEach(service::execute);
        latch.await();
        assertEquals(totalThreads, counter.getCounter());
    }

    @Test
    @DisplayName("Game id generator should calculate correct game ID during concurrency processing")
    void shouldCountSumCorrectlyConcurrency() throws InterruptedException {
        int totalThreads = 10;
        ExecutorService service = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(totalThreads);
        GameIDCounter counter = new GameIDCounter();
        IntStream.range(0, totalThreads).<Runnable>mapToObj(i -> () -> {
            counter.incrementAndGet();
            latch.countDown();
        }).forEach(service::submit);
        latch.await();
        assertEquals(totalThreads, counter.getCounter());
    }
}