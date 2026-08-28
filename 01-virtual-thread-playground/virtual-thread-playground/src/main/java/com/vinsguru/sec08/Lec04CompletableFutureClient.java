package com.vinsguru.sec08;

import com.vinsguru.sec08.external.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class Lec04CompletableFutureClient {


    static void main() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> product1 = CompletableFuture.supplyAsync(() -> Client.getProduct(1), executor);
            Future<String> product2 = CompletableFuture.supplyAsync(() -> Client.getProduct(2), executor);
            Future<String> product3 = CompletableFuture.supplyAsync(() -> Client.getProduct(3), executor);

            //blocking operation
            var response1 = product1.get();
            var response2 = product2.get();
            var response3 = product3.get();

            log.info("product-1: {}", response1);
            log.info("product-2: {}", response2);
            log.info("product-3: {}", response3);

        }
    }
}
