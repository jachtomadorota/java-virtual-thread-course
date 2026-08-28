package com.vinsguru.sec08;

import com.vinsguru.sec08.aggregator.AggregatorService;
import com.vinsguru.sec08.aggregator.Product;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

@Slf4j
public class Lec05AggregatorDemo {


    static void main() throws ExecutionException, InterruptedException {
        var executor = Executors.newVirtualThreadPerTaskExecutor();
        var aggregator = new AggregatorService(executor);

        var results = IntStream.rangeClosed(1, 50)
                .mapToObj(id -> executor.submit(() -> aggregator.getProduct(id)))
                .toList();

        var finalResult = results.stream()
                        .map(Lec05AggregatorDemo::toProductDto)
                .toList();

        log.info("result: {}", finalResult);
        log.info("product-1: {}", aggregator.getProduct(1));
    }

    private static Product toProductDto(Future<Product> future) {
        try {
            return future.get();
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
