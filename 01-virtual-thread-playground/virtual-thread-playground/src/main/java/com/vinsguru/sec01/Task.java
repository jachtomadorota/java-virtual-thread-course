package com.vinsguru.sec01;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class Task {

    public static void IOIntensiveTask(int i) {
        try {
            log.info("starting I/O task {} with current thread: {}", i, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(10));
            log.info("ending I/O task {} with current thread: {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
