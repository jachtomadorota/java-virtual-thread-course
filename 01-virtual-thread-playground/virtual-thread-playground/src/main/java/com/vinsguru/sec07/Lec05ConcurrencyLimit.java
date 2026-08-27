package com.vinsguru.sec07;

import com.vinsguru.sec07.external.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j

public class Lec05ConcurrencyLimit {

    static void main() {
        var factory = Thread.ofVirtual().name("dorota", 1).factory();
        execute(Executors.newFixedThreadPool(3, factory), 20);
    }

    private static void execute(ExecutorService executorService, int taskCount) {
        try (executorService) {
            for (int i = 1; i < taskCount; i++) {
                int j = i;
                executorService.submit(() -> printProductInfo(j));
            }
            log.info("submitted");
        }
    }

    private static void printProductInfo(int id) {
        log.info("{} => {}", id, Client.getProduct(id));
    }
}
