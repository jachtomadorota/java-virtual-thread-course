package com.vinsguru.sec07;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.Executors;

@Slf4j
public class Lec01AutoClosable {


    static void main() {
        try (var executor = Executors.newSingleThreadExecutor()) {
            executor.submit(Lec01AutoClosable::task);
            log.info("submitted");
        }
    }

    private static void task() {
        CommonUtils.sleep(Duration.ofSeconds(1));
        log.info("task executed");
    }
}
