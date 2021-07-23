package com.brianxia.shardingjdbcmybatis;

import com.brianxia.shardingjdbcmybatis.entity.BOrder;
import com.brianxia.shardingjdbcmybatis.mapper.BOrderMapper;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.apache.shardingsphere.transaction.core.TransactionTypeHolder;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Random;

@SpringBootTest
class ShardingJdbcMybatisApplicationTests {

    @Autowired
    private BOrderMapper bOrderMapper;

    @Test
    //@RepeatedTest(2)
    public void testShardingBOrder(){
        Random random = new Random();
        int companyId = random.nextInt(10);
        BOrder order = new BOrder();
        order.setIsDel(false);
        order.setCompanyId(companyId);
        order.setPositionId(3242342);
        order.setUserId(2222);
        order.setPublishUserId(1111);
        order.setResumeType(1);
        order.setStatus("AUTO");
        order.setCreateTime(new Date());
        order.setOperateTime(new Date());
        order.setWorkYear("2");
        order.setName("heima");
        order.setPositionName("Java");
        order.setResumeId(23233);
        bOrderMapper.insert(order);
    }

    @Test
    //@RepeatedTest(2)
    @Transactional
    //手动控制是否回滚
    @Rollback(true)
    public void testShardingBOrder2(){
        TransactionTypeHolder.set(TransactionType.XA);
        for (int i = 0; i < 100; i++) {
            Random random = new Random();
            int companyId = random.nextInt(10);
            BOrder order = new BOrder();
            order.setIsDel(false);
            order.setCompanyId(companyId);
            order.setPositionId(3242342);
            order.setUserId(2222);
            order.setPublishUserId(1111);
            order.setResumeType(1);
            order.setStatus("AUTO");
            order.setCreateTime(new Date());
            order.setOperateTime(new Date());
            order.setWorkYear("2");
            order.setName("heima");
            order.setPositionName("Java");
            order.setResumeId(23233);
            bOrderMapper.insert(order);
        }

    }
}
