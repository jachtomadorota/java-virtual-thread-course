package com.vinsguru.sec07;

import com.vinsguru.sec07.external.Client;
import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Lec07ScheduledExecutorWithVirtualThreads {

    static void main() {
        scheduled();
    }


    private static void scheduled() {
        var scheduler = Executors.newSingleThreadScheduledExecutor();
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        try(scheduler;executor) {
            scheduler.scheduleAtFixedRate(() -> {
                executor.submit(() -> printProductInfo(1));
                log.info("executing task");
            }, 0, 1, TimeUnit.SECONDS);
            CommonUtils.sleep(Duration.ofSeconds(10));
        }
    }

    private static void printProductInfo(int id) {
        log.info("{} => {}", id, Client.getProduct(id));
    }
}
