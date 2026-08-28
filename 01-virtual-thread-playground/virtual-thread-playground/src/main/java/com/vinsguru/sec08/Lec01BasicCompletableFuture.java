package com.vinsguru.sec08;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class Lec01BasicCompletableFuture {


    static void main() {
        log.info("starting task");
        var result = slowTask();
        result.thenAccept(v -> log.info("value: {}", v));
        log.info("ending task");
        CommonUtils.sleep(Duration.ofSeconds(3));
    }

    private static CompletableFuture<String> fastTask() {
        log.info("fastTask starts");
        var cf = new CompletableFuture<String>();
        cf.complete("Hello");
        log.info("fastTask ends");
        return cf;
    }

    private static CompletableFuture<String> slowTask() {
        log.info("slowTask starts");
        var cf = new CompletableFuture<String>();
        Thread.ofVirtual().start(() -> {
            CommonUtils.sleep(Duration.ofSeconds(1));
            cf.complete("Hello");
        });
        log.info("slowTask ends");
        return cf;
    }
}
