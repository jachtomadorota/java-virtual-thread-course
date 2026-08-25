package com.vinsguru.sec03;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class CPUTaskDemo {

    private static final int TASKS_COUNT = 3 * Runtime.getRuntime().availableProcessors();

    static void main() {
        log.info("Task count:{}", TASKS_COUNT);
        for (int i = 0; i < 3; i++) {
            var totalTimeTaken = CommonUtils.timer(() -> demo(Thread.ofVirtual()));
            log.info("Total time taken of Virtual Threads: {}", totalTimeTaken);
        }

        for (int i = 0; i < 3; i++) {
            var totalTimeTaken = CommonUtils.timer(() -> demo(Thread.ofVirtual()));
            log.info("Total time taken of Platform Threads: {}", totalTimeTaken);
        }
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
