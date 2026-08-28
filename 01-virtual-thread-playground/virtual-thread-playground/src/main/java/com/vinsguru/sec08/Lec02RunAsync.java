package com.vinsguru.sec08;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

@Slf4j
public class Lec02RunAsync {

    static void main() {
        log.info("main starts");
        runAsync()
                .thenRun(() -> log.info("task is ended"))
                .exceptionally(throwable -> {
                    log.info("error occured: {}", throwable.getMessage());
                    return null;
                });
        log.info("main ends");
        CommonUtils.sleep(Duration.ofSeconds(3));

    }


    private static CompletableFuture<Void> runAsync() {
        log.info("runAsync starts");
        return CompletableFuture.runAsync(() -> {
            log.info("Async task is running");
            CommonUtils.sleep(Duration.ofSeconds(1));
            throw new RuntimeException("Ooooops");
        }, Executors.newVirtualThreadPerTaskExecutor());
    }


}
