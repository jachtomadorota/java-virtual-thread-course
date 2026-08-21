package com.vinsguru.sec01;

import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM = 10000;

    static void main() {
        platformThreadDemo4();

    }

    private static void platformThreadDemo() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.IOIntensiveTask(j));
            thread.start();
        }
    }

    private static void platformThreadDemo2() {
        Thread.Builder.OfPlatform builder = Thread.ofPlatform().name("dorota", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.IOIntensiveTask(j));
            thread.start();
        }
    }


    private static void platformThreadDemo3() {
        Thread.Builder.OfPlatform builder = Thread.ofPlatform().name("dorota", 1).daemon();
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> Task.IOIntensiveTask(j));
            thread.start();
        }
    }

    private static void platformThreadDemo4() {
        var latch = new CountDownLatch(MAX_PLATFORM);
        Thread.Builder.OfPlatform builder = Thread.ofPlatform().name("dorota", 1).daemon();
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.IOIntensiveTask(j);
                latch.countDown();
            });
            thread.start();
        }
    }
}
