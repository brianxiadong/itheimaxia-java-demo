package com.brianxia.coupondemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.brianxia.coupondemo.entity.CouponDO;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>

 * @since 2021-02-07
 */
public interface CouponMapper extends BaseMapper<CouponDO> {

    /**
     * 扣减存储
     * @param couponId
     * @return
     */
    int reduceStock(@Param("couponId") long couponId,@Param("size") int size);
}
