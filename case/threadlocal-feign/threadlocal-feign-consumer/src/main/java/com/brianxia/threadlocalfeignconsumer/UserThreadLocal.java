package com.brianxia.threadlocalfeignconsumer;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/3 18:33
 */
public interface UserThreadLocal {
    public static ThreadLocal<String> userId = new ThreadLocal<>();
}
