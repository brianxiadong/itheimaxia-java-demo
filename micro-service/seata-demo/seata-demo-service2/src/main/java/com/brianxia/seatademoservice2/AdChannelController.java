package com.brianxia.seatademoservice2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdChannelController {

    @Autowired
    private AdChannelMapper adChannelMapper;


    @PostMapping("/save")
    public void save(@RequestBody AdChannel adChannel) {
        adChannelMapper.insert(adChannel);
    }

}
