package com.vinsguru.sec08.aggregator;

import com.vinsguru.sec07.external.Client;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class AggregatorService {

    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Product getProduct(int id) throws ExecutionException, InterruptedException {
        var product = CompletableFuture.supplyAsync(() -> Client.getProduct(id), executorService);
        var rating = CompletableFuture.supplyAsync(() -> Client.getRating(id), executorService)
                .exceptionally(ex -> {
                    return "-1";
                });
        return new Product(id, product.get(), rating.get());
    }
}
