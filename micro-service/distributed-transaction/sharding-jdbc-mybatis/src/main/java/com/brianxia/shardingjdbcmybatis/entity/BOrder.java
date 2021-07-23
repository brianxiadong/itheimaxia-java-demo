package com.brianxia.shardingjdbcmybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


@TableName("b_order")
@Data
public class BOrder implements Serializable {

    @TableId(value = "id")
    private Long id;

    private Boolean isDel;

    private Integer companyId;

    private long positionId;

    private Integer userId;

    private Integer publishUserId;

    private Integer resumeType;

    private String status;

    private Date createTime;

    private Date operateTime;

    private String workYear;

    private String name;

    private String positionName;

    private Integer resumeId;

}
