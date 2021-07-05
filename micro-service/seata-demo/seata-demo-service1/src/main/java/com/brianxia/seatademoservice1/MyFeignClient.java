package com.brianxia.seatademoservice1;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/3 19:20
 */
@FeignClient("seata-demo-service2")
public interface MyFeignClient {
    @PostMapping("/save")
    public void save(@RequestBody AdChannel adChannel);

}
