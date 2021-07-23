package com.brianxia.shardingjdbcmybatis;

import com.brianxia.shardingjdbcmybatis.entity.Position;
import com.brianxia.shardingjdbcmybatis.mapper.PositionMapper;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author brianxia
 * @version 1.0
 * @date 2021/5/27 18:45
 */
@SpringBootTest
public class BaseTest {

    @Autowired
    private PositionMapper positionMapper;


    @Test
    public void add(){
        for (long i = 0; i < 100; i++) {
            Position position = new Position();
            position.setId(i);
            positionMapper.insert(position);
        }
    }
}
