package com.vinsguru.sec07;

import com.vinsguru.sec07.external.Client;
import com.vinsguru.sec07.limiter.ConcurrencyLimiter;
import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.Executors;

@Slf4j

public class Lec06ConcurrencyLimitWithSemaphore {

    static void main() throws Exception {
        var factory = Thread.ofVirtual().factory();
        execute(new ConcurrencyLimiter(Executors.newThreadPerTaskExecutor(factory), 30), 100);
        CommonUtils.sleep(Duration.ofSeconds(10));
    }

    private static void execute(ConcurrencyLimiter concurrencyLimiter, int taskCount) throws Exception {
        try (concurrencyLimiter) {
            for (int i = 1; i < taskCount; i++) {
                int j = i;
                concurrencyLimiter.submit(() -> printProductInfo(j));
            }
            log.info("submitted");
        }
    }

    private static String printProductInfo(int id) {
        var product = Client.getProduct(id);
        log.info("{} => {}", id, Client.getProduct(id));
        return product;
    }
}
