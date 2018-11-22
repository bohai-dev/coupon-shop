package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShopMapper {
    int deleteByPrimaryKey(String shopId);


    int insertSelective(Shop record);

    Shop selectByPrimaryKey(String shopId);

    int updateByPrimaryKeySelective(Shop record);


    @Select(value="select SHOP_SEQ.NEXTVAL from dual")
    String generateUserId();

    //根据用户编号查询店铺列表
    @Select(value = "select * from shop where admin_user_id=#{userId} and is_delete='0'")
    List<Shop> selectByUserId(String userId);

   /* @Select(value = "select * from shop where class_id=#{classId} and is_delete='0'")
    List<Shop> selectByClassId(String classId);*/

   List<Shop> selectConditions(Shop shop);
}