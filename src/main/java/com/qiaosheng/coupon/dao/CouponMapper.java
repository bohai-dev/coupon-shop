package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

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

    //根据店铺编号查询优惠券列表
    @Select("select * from coupon where shop_id=#{shopId} and is_delete='0'")
    List<Coupon> selectByShopId(@Param("shopId") String shopId);

    //根据店铺编号逻辑删除优惠券
    @Update("update coupon set is_delete='1',update_time=sysdate where shop_id=#{shopId}")
    int deleteByShopId(@Param("shopId") String shopId);
}