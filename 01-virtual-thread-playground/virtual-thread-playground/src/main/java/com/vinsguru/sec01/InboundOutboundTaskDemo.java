package com.vinsguru.sec01;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM = 10;

    static void main() {
        platformThreadDemo();

    }

    private static void platformThreadDemo() {
        for (int i = 0; i < MAX_PLATFORM; i++) {
            int finalI = i;
            Thread thread = new Thread(() -> Task.IOIntensiveTask(finalI));
        }
    }
}
