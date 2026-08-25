package com.vinsguru.sec02;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class StackTraceDemo {


    static void main() {
        //demo(Thread.ofPlatform());
        demo(Thread.ofVirtual());

    }

    private static void demo(Thread.Builder builder) {
        for(int i = 0; i <= 20; i++) {
            int j = i;
            builder.start(() -> Task.executeMethod(j));
        }
    }
}
