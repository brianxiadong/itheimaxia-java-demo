package com.brianxia.threadlocalfeignprovider.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/3 18:28
 */
@RestController
public class ProviderController {

    @GetMapping("/test")
    public String getHeader(HttpServletRequest request) {
        return request.getHeader("userId");
    }
}
