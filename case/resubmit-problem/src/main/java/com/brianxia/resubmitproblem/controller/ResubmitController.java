package com.brianxia.resubmitproblem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Arrays;
import java.util.UUID;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/5 15:47
 * 解决重复提交问题，使用redis原子性操作
 */
@RestController
public class ResubmitController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 获取token接口
     * @param seqId 流水号
     * @return
     */
    @GetMapping("/token")
    public String getToken(String seqId){
        String s = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set("resubmit:" + seqId,s, Duration.ofMinutes(30));
        return s;
    }

    /**
     * 提交表单接口
     * @param token
     * @param seqId 流水号
     * @return
     */
    @GetMapping("/submit")
    public String submit(String token,String seqId){
        //原子操作 校验令牌，删除令牌
        String script = "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end";

        Long result = stringRedisTemplate.execute(new DefaultRedisScript<>(script,Long.class), Arrays.asList("resubmit:" + seqId),token);
        if(result == 0L){
            return "重复下单了";
        }

        return "下单成功";

    }
}
