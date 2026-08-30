package com.vinsguru.sec08;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;

@Slf4j
public class Lec01ThreadLocal {

    private static final ThreadLocal<String> sessionTokenHolder = new ThreadLocal<String>();

    static void main() {


    }

    private static String authenticate() {
        var token = UUID.randomUUID().toString();
        log.info("toke: {}", token);
        return token;
    }

    private static void orderController() {
        log.info("orderController: {}", sessionTokenHolder.get());
    }




}
