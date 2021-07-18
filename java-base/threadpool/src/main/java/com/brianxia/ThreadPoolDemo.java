package com.brianxia;

import java.util.concurrent.*;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/18 10:27
 */
public class ThreadPoolDemo {
    public static void main(String[] args) {
        /**
         * int corePoolSize,        核心线程数
         * int maximumPoolSize,     最大线程数
         *                               long keepAliveTime,  等待时间
         *                               TimeUnit unit,  单位
         *                               BlockingQueue<Runnable> workQueue,
         *                               ThreadFactory threadFactory,
         *                               RejectedExecutionHandler handler
         */
        ThreadPoolExecutor zhangsan = new ThreadPoolExecutor(10, 100, 10, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(1000), r -> {
            //定制化线程创建
            Thread thread = new Thread(r);
            thread.setName("zhangsan");
            return thread;
        }, new ThreadPoolExecutor.CallerRunsPolicy());

        zhangsan.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("内容");
            }
        });
    }
}
