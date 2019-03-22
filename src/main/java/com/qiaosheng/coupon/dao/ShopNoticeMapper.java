package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.ShopNotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ShopNoticeMapper {
    int deleteByPrimaryKey(String noticeId);

    int insert(ShopNotice record);

    int insertSelective(ShopNotice record);

    ShopNotice selectByPrimaryKey(String noticeId);

    int updateByPrimaryKeySelective(ShopNotice record);

    int updateByPrimaryKey(ShopNotice record);

    int insertList(List<ShopNotice> list);

    @Select("select * from SHOP_NOTICE where IS_DELETE=0")
    List<ShopNotice> selectAll();
}