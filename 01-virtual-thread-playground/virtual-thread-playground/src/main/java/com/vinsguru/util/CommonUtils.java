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
}
