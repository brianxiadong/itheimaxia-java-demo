package com.brianxia.threadlocaldemo.controller;

import com.brianxia.threadlocaldemo.threadlocal.MyThreadLocal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/6/28 0:29
 */
@RestController
public class TestController {

    @GetMapping("/test")
    public void test(){
        System.out.println(MyThreadLocal.myThreadLocal.get());
    }
}
