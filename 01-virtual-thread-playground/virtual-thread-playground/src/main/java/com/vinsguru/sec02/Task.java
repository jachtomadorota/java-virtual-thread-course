package com.vinsguru.sec02;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;

@Slf4j
public class Task {

    public static void executeMethod(int i) {
        log.info("starting task....");
        try {
            method1(i);
        } catch (Exception e) {
            log.error("Error: {}", e.getMessage());
        }
        log.info("ending task....");
    }

    private static void method1(int i) {
        CommonUtils.sleep(Duration.ofMillis(300));
        try {
            method2(i);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void method2(int i) {
        CommonUtils.sleep(Duration.ofMillis(100));
        method3(1);
    }

    private static void method3(int i) {
        CommonUtils.sleep(Duration.ofMillis(500));
        if (i == 4) {
            throw new IllegalArgumentException("i cannot be 4");
        }
    }

}
