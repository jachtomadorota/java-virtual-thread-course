package com.vinsguru.sec08;

import com.vinsguru.sec07.aggregator.Product;
import com.vinsguru.sec08.aggregator.AggregatorService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class Lec06AllOff {

    static void main() {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        var results = IntStream.rangeClosed(1, 50)
                .mapToObj(id -> CompletableFuture.supplyAsync(() -> {
                    try {
                        return aggregator.getProduct(id);
                    } catch (ExecutionException | InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }));

        CompletableFuture.allOf(results.toArray(CompletableFuture[]::new)).join();
    }

    private static Product toProductDto(Future<Product> future) {
        try {
            return future.get();
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
