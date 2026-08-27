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
            Future<String> future = executor.submit(() -> Client.getProduct(1));
            //blocking operation
            var response = future.get();
            log.info("product-1: {}", response);
        }
    }
}
