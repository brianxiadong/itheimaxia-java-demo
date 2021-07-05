package com.brianxia.seatademoservice1;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdChannelController {

    @Autowired
    private AdChannelMapper adChannelMapper;

    @Autowired
    private MyFeignClient myFeignClient;

    @PostMapping("/save")
    @GlobalTransactional
    public void save(@RequestBody AdChannel adChannel) {
        myFeignClient.save(adChannel);
        adChannelMapper.insert(adChannel);
    }

}
