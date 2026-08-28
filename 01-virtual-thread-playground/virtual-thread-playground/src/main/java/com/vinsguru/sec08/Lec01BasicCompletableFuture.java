package com.vinsguru.sec08;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
public class Lec01BasicCompletableFuture {


    static void main() throws ExecutionException, InterruptedException {
        log.info("starting task");
        var result = fastTask();
        log.info("result: {}", result.get());
        log.info("result: {}", result.join());
        log.info("ending task");
    }

    private static CompletableFuture<String> fastTask() {
        log.info("fastTask starts");
        var cf = new CompletableFuture<String>();
        cf.complete("Hello");
        log.info("fastTask ends");
        return cf;
    }
}
