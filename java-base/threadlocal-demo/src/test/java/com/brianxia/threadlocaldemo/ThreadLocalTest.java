package com.brianxia.threadlocaldemo;

import org.junit.jupiter.api.Test;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/6/28 0:35
 */
public class ThreadLocalTest {

    @Test
    public void test(){
        ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();
        stringThreadLocal.set("123");

        stringThreadLocal = null;
        System.gc();
        System.runFinalization();
        System.gc();
        Thread thread = Thread.currentThread();
    }
}
