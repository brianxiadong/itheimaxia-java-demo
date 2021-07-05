package com.brianxia.sentineldemo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/5 16:35
 */
@RestController
public class SentinelController {

    @GetMapping("/test")
    public String test() {
        return "aaa";
    }
}
