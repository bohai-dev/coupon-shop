package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.SpecialDishes;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface SpecialDishesMapper {
    int deleteByPrimaryKey(String dishId);


    int insertSelective(SpecialDishes record);

    SpecialDishes selectByPrimaryKey(String dishId);

    int updateByPrimaryKeySelective(SpecialDishes record);


    int insertList(List<SpecialDishes> specialDishes);

    @Select("select * from SPECIAL_DISHES where SHOP_ID=#{shopId} AND IS_DELETE='0'")
    List<SpecialDishes> selectByShopId(@Param("shopId")String shopId);

    //根据店铺编号逻辑删除优惠券
    @Update("update SPECIAL_DISHES set IS_DELETE='1' where SHOP_ID=#{shopId}")
    int deleteByShopId(@Param("shopId") String shopId);
}