package com.brianxia.threadlocalfeignconsumer.controller;

import com.brianxia.threadlocalfeignconsumer.feign.ProviderFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/3 18:30
 */
@RestController
public class ConsumerController {

    @Autowired
    private ProviderFeign providerFeign;

    @GetMapping("/test")
    public String getHeader() {
        return providerFeign.getHeader();
    }

}
