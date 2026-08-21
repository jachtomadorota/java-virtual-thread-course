package com.vinsguru.sec01;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM = 10000;

    static void main() {
        platformThreadDemo2();

    }

    private static void platformThreadDemo() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.IOIntensiveTask(j));
            thread.start();
        }
    }

    private static void platformThreadDemo2() {
        Thread.Builder.OfPlatform thread = Thread.ofPlatform().name("dorota", 1);
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int j = i;
            thread.start(() -> Task.IOIntensiveTask(j));
        }
    }
}
