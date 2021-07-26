package com.brianxia.threadlocaldemo;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/24 16:58
 */
public class ThreadLocalSimpleTest {
    //2.静态变量创建ThreadLocal对象
    public static ThreadLocal<String> abc = new ThreadLocal<>();
    public static Integer i = 1;

    public static void main(String[] args) {
        //1.创建两个线程
        Thread thread = new Thread(() -> {
            //3.第一个线程写入数据 第二个线程读取数据
            abc.set("thread1"); //线程map -> key: abc对象引用 value: thread1
            System.out.println("thread1ok");
            System.out.println(abc.get()); //get -> "thread1"
            i = 2;
        });
        thread.start();
        Thread thread2 = new Thread(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //3.第一个线程写入数据 第二个线程读取数据
            String s = abc.get(); //map 空 -> null
            System.out.println(s);
            System.out.println(i);
        });
        thread2.start();

    }
}
