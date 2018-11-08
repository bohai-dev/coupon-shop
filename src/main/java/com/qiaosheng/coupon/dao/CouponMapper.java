package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CouponMapper {
    int deleteByPrimaryKey(String couponId);

    int insert(Coupon record);

    int insertSelective(Coupon record);

    Coupon selectByPrimaryKey(String couponId);

    int updateByPrimaryKeySelective(Coupon record);

    int updateByPrimaryKey(Coupon record);

    @Select(value="select COUPON_SEQ.NEXTVAL from dual")
    String generateUserId();
}