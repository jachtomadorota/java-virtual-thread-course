package com.vinsguru.sec08;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Lec07AnyOf {


    static void main() {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var cf1 = getDeltaAirfare(executor);
            var cf2 = getFrontierAirfare(executor);
            log.info("airfare={}", CompletableFuture.anyOf(cf1, cf2).join());
        }
    }

    private static CompletableFuture<String> getDeltaAirfare(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 10000);
            CommonUtils.sleep(Duration.ofMillis(random));
            return "Delta-$ " + random;
        }, executorService);
    }

    private static CompletableFuture<String> getFrontierAirfare(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 10000);
            CommonUtils.sleep(Duration.ofMillis(random));
            return "Frontier-$ " + random;
        }, executorService);
    }
}
