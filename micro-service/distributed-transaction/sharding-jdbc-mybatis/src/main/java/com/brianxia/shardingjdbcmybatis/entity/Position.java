package com.brianxia.shardingjdbcmybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@TableName("position")
@Data
public class Position implements Serializable {
    @TableId(value = "id",type = IdType.INPUT)
    private Long id;
    private String name;
    private String salary;
    private String city;
}
