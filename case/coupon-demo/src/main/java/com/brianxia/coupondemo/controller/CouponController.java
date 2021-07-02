package com.brianxia.coupondemo.controller;

import com.brianxia.coupondemo.entity.CouponDO;
import com.brianxia.coupondemo.mapper.CouponMapper;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/7/2 14:28
 */
@RestController
public class CouponController {

    @Autowired
    private CouponMapper couponMapper;

    /**
     * 第一种写法，会产生数据扣减和实际扣减数不一致
     * @param size
     */
    @GetMapping("/1")
    public void coupon1(Integer size){
        CouponDO couponDO = couponMapper.selectById(19);
        if(couponDO.getStock() > 0){
            couponDO.setStock(couponDO.getStock() - size);
        }
        couponMapper.updateById(couponDO);
    }

    /**
     * 第二种写法 可以正常扣减库存
     * @param size
     */
    @GetMapping("/2")
    public void coupon2(Integer size){
        int i = couponMapper.reduceStock(19, 1);
        if(i == 0){
            throw new RuntimeException();
        }
    }

    @Autowired
    private RedissonClient redissonClient;
    /**
     * 第三种写法 分布式锁
     * @param size
     */
    @GetMapping("/3")
    public void coupon3(Integer size){
        RLock lock = redissonClient.getLock("lock:redisson:coupon:19");
        lock.lock();
        try{
            CouponDO couponDO = couponMapper.selectById(19);
            if(couponDO.getStock() > 0){
                couponDO.setStock(couponDO.getStock() - size);
            }
            couponMapper.updateById(couponDO);
        }finally {
            lock.unlock();
        }

    }
}
