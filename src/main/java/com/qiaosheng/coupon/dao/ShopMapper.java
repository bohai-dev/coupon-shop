package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(String shopId);

    int insert(Shop record);

    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(Shop record);

    int updateByPrimaryKey(Shop record);

    @Select(value="select SHOP_SEQ.NEXTVAL from dual")
    String generateUserId();
}