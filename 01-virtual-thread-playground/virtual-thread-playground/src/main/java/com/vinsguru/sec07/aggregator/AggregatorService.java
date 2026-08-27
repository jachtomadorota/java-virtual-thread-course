package com.vinsguru.sec07.aggregator;

import com.vinsguru.sec07.external.Client;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

public class AggregatorService {

    private final ExecutorService executorService;

    public AggregatorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Product getProduct(int id) throws ExecutionException, InterruptedException {
        var product = executorService.submit(() -> Client.getProduct(id));
        var rating = executorService.submit(() -> Client.getRating(id));
        return new Product(id, product.get(), rating.get());
    }
}
