package com.brianxia.threadlocalfeignconsumer.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/3 18:29
 */
@FeignClient(value = "threadlocal-feign-provider")
public interface ProviderFeign {
    @GetMapping("/test")
    public String getHeader();
}
