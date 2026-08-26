package com.vinsguru.sec06;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.concurrent.ThreadFactory;

@Slf4j
public class Lec01ThreadFactory {

    static void main() {
        demo(Thread.ofVirtual().name("dorota", 1).factory());
        CommonUtils.sleep(Duration.ofSeconds(6));
    }

    private static void demo(ThreadFactory factory) {
        for (int i = 0; i < 50; i++) {
            factory.newThread(() -> {
                log.info("Update started. {}", Thread.currentThread());
                factory.newThread(() -> {
                    CommonUtils.sleep(Duration.ofSeconds(2));
                    log.info("Execution of child thread");
                }).start();;
                log.info("Update ended. {}", Thread.currentThread());
            });
        }
    }

}
