package com.vinsguru.sec05;

import com.vinsguru.util.CommonUtils;
import lombok.extern.slf4j.Slf4j;


import java.time.Duration;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class Lec04ReentrantLockWithIO {

    private final static Lock lock = new ReentrantLock();

    /*
        Use this to check if virtual threads are getting pinned in your application
        -Djdk.tracePinnedThreads=full
        -Djdk.tracePinnedThreads=short
    */
    static {
        System.setProperty("jdk.tracePinnedThreads", "short");
    }

    static void main(String[] args) {

        demo(Thread.ofVirtual());

        CommonUtils.sleep(Duration.ofSeconds(15));

    }

    private static void demo(Thread.Builder builder){

        // 50 threads attempting to update the shared document (synchronized, runs sequentially)
        for (int i = 0; i < 50; i++) {
            builder.start(() -> {
                log.info("Update started. {}", Thread.currentThread());
                updateSharedDocument();
                log.info("Update ended. {}", Thread.currentThread());
            });
        }

        // 3 threads fetching user profiles (runs concurrently)
        for (int i = 0; i < 3; i++) {
            builder.start(() -> {
                log.info("Fetch started. {}", Thread.currentThread());
                fetchUserProfile();
                log.info("Fetch ended. {}", Thread.currentThread());
            });
        }

    }

    // IO Task 1 - requires synchronization
    private static void updateSharedDocument(){
        try {
            lock.lock();
            CommonUtils.sleep(Duration.ofSeconds(10));
        } finally {
            lock.unlock();
        }
    }

    // IO Task 2
    private static void fetchUserProfile(){
        CommonUtils.sleep(Duration.ofSeconds(1));
    }


}
