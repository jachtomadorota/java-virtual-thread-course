package com.vinsguru.sec07;

import com.vinsguru.sec07.external.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class Lec03AccessResponseUsingFuture {


    static void main() throws ExecutionException, InterruptedException {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Future<String> product1 = executor.submit(() -> Client.getProduct(1));
            Future<String> product2 = executor.submit(() -> Client.getProduct(2));
            Future<String> product3 = executor.submit(() -> Client.getProduct(3));

            //blocking operation
            var response1 = product1.get();
            var response2 = product2.get();
            var response3 = product3.get();

            log.info("product-1: {}", product1);
        }
    }
}
