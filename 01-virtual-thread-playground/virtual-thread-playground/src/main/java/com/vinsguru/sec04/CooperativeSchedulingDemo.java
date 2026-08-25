package com.vinsguru.sec04;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class CooperativeSchedulingDemo {

    static {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "1");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "1");
    }

    static void main() {

        var builder = Thread.ofVirtual();
        var t1 = builder.unstarted(() -> demo(1));
        var t2 = builder.unstarted(() -> demo(1));

        t1.start();
        t2.start();
        CommonUtils.sleep(Duration.ofSeconds(2));
    }

    private static void demo(int threadNumber) {
        log.info("Thread-{} started", threadNumber);

        for (int i = 0; i < 10 ; i++) {
            log.info("Thread-{} is printing {}. Thread: {}", threadNumber, i, Thread.currentThread());
            Thread.yield();
        }

        log.info("Thread-{} ended", threadNumber);

    }
}
