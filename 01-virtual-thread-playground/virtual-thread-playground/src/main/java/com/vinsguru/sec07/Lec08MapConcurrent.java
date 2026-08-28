package com.vinsguru.sec07;

import com.vinsguru.sec07.external.Client;
import lombok.extern.slf4j.Slf4j;

import java.util.stream.Gatherers;
import java.util.stream.IntStream;

@Slf4j
public class Lec08MapConcurrent {

    static void main() {
        var results = IntStream.rangeClosed(1, 50)
                .boxed()
                .gather(Gatherers.mapConcurrent(50, Lec08MapConcurrent::printProductInfo))
                .toList();
    }

    private static String printProductInfo(int id) {
        var product = Client.getProduct(id);
        log.info("{} => {}", id, Client.getProduct(id));
        return product;
    }
}
