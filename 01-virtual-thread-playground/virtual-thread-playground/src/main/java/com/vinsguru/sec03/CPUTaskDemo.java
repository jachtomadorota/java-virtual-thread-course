package com.vinsguru.sec03;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CPUTaskDemo {

    private static final int TASKS_COUNT = 2 * Runtime.getRuntime().availableProcessors();

    static void main() {
        demo(Thread.ofPlatform());
    }
    
    private static void demo(Thread.Builder builder) {
        var latch = new CountDownLatch(TASKS_COUNT);
        for (int i = 0; i < TASKS_COUNT; i++) {
            builder.start(() -> {
                Task.CPUIntensiveTask(45);
                latch.countDown();
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
