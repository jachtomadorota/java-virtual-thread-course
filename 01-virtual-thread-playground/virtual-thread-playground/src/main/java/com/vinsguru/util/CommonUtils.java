package com.vinsguru.util;

import lombok.experimental.UtilityClass;

import java.time.Duration;

@UtilityClass
public class CommonUtils {


    public static void sleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException exception) {
            throw new RuntimeException();
        }
    }

    public static long timer(Runnable runnable) {
        var start = System.currentTimeMillis();
        runnable.run();
        var end = System.currentTimeMillis();
        return (end - start);
    }
}
