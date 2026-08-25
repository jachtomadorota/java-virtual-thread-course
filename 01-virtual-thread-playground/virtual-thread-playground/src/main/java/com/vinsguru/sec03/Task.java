package com.vinsguru.sec03;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task {


    public static void CPUIntensiveTask(int i) {
        log.info("Starting CPU task. Thread info: {}", Thread.currentThread());
        var timeTaken = CommonUtils.timer(() -> fibSequence(i));
        log.info("ending CPU task, time taken: {} ms.", timeTaken);
    }

    public static long fibSequence(long input) {
        if (input < 2) {
            return input;
        } else {
            return fibSequence(input - 1) + fibSequence(input - 2);
        }
    }
}
