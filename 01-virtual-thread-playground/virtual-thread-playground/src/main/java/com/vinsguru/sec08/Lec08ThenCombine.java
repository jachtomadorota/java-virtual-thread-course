package com.vinsguru.sec08;

import com.vinsguru.sec08.support.Airfare;
import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class Lec08ThenCombine {


    static void main() {
        try(var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var cf1 = getDeltaAirfare(executor);
            var cf2 = getFrontierAirfare(executor);
            var result = cf1.thenCombine(cf2, (a,b) -> a.value() <= b.value() ? a : b);
            log.info("airfare={}", result.join());
        }
    }

    private static CompletableFuture<Airfare> getDeltaAirfare(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 10000);
            CommonUtils.sleep(Duration.ofMillis(random));
            return new Airfare("Delta-$ " + random, random);
        }, executorService);
    }

    private static CompletableFuture<Airfare> getFrontierAirfare(ExecutorService executorService) {
        return CompletableFuture.supplyAsync(() -> {
            var random = ThreadLocalRandom.current().nextInt(100, 10000);
            CommonUtils.sleep(Duration.ofMillis(random));
            return new Airfare("Frontier-$ " + random, random);
        }, executorService);
    }
}
