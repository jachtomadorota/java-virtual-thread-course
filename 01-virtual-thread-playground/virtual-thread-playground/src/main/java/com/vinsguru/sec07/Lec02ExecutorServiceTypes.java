package com.vinsguru.sec07;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Lec02ExecutorServiceTypes {

    static void main() {
        //execute(Executors.newSingleThreadExecutor(), 5);
        //execute(Executors.newFixedThreadPool(4), 4);
        //execute(Executors.newCachedThreadPool(), 5);
        //execute(Executors.newVirtualThreadPerTaskExecutor(), 100);
        scheduled();

    }

    private static void scheduled() {
        try(var executorService = Executors.newSingleThreadScheduledExecutor()) {
            executorService.scheduleAtFixedRate(() -> {
                log.info("executing task");
            }, 0, 1, TimeUnit.SECONDS);
            CommonUtils.sleep(Duration.ofSeconds(10));
        }
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 0; i < taskCount; i++) {
                int j = i;
                executorService.submit(() -> IOTask(j));
            }
            log.info("submitted");
        }
    }

    private static void IOTask(int i) {
        log.info("Task started: {}. Thread info: {}", i, Thread.currentThread());
        CommonUtils.sleep(Duration.ofSeconds(5));
        log.info("Task ended: {}. Thread info: {}", i, Thread.currentThread());
    }

}
