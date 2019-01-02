package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.vo.CouponCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cxy on 2019/1/2
 */
@Mapper
public interface CouponCountMapper {

    @Select("select a.SHOP_ID,b.SHOP_NAME,count(a.COUPON_ID) as counponCount,a.COUPON_STATE from CUST_COUPON_RECORD a ,shop b where a.SHOP_ID = b.SHOP_ID  group by a.SHOP_ID,a.COUPON_STATE,b.SHOP_NAME order by a.SHOP_ID")
    List<CouponCount> selectCount();
}
