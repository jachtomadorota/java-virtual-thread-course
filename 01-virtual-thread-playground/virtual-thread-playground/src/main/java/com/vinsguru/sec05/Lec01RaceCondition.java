package com.vinsguru.sec05;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Lec01RaceCondition {

    private static final List<Integer> list = new ArrayList<>();

    static void main() {
        demo(Thread.ofVirtual());
        CommonUtils.sleep(Duration.ofSeconds(5));
        log.info("List size: {}", list.size());
    }

    private static void demo(Thread.Builder builder) {
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Task started: {}", Thread.currentThread());
                for (int j = 0; j < 100; j++) {
                    inMemoryTask();
                }
                log.info("Task ended: {}", Thread.currentThread());
            });
        }
    }

    private static synchronized void inMemoryTask() {
        list.add(1);
    }
}
