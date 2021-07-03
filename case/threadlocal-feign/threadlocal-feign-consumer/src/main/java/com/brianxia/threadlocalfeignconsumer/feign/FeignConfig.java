package com.brianxia.threadlocalfeignconsumer.feign;

import com.brianxia.threadlocalfeignconsumer.UserThreadLocal;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/3 18:34
 */
@Configuration
public class FeignConfig {
    @Bean
    RequestInterceptor requestInterceptor(){
        return requestTemplate -> {
            requestTemplate.header("userId", UserThreadLocal.userId.get());
        };
    }
}
