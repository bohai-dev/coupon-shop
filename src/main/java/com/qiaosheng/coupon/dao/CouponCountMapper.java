package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.vo.CouponCount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by cxy on 2019/1/2
 */
@Mapper
public interface CouponCountMapper {

    /**
     * 统计优惠券兑换数量
     * @return
     */
    @Select("select a.SHOP_ID,b.SHOP_NAME,count(a.COUPON_ID) as COUPON_COUNT,a.COUPON_STATE from CUST_COUPON_RECORD a ,shop b where a.SHOP_ID = b.SHOP_ID AND to_char(a.CREATE_TIME,'yyyy-mm-dd')>=#{startTime} And  to_char(a.CREATE_TIME,'yyyy-mm-dd')<=#{endTime} group by a.SHOP_ID,a.COUPON_STATE,b.SHOP_NAME order by a.SHOP_ID")
    List<CouponCount> selectCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    /**
     * 统计各店铺详情页浏览量、转发按钮点击量、分享卡点击量
     * @return
     */
    @Select("select c.shop_id,c.shop_name,c.scan_times,nvl(c.button_count,0) as button_count,nvl(d.share_count,0) as share_count from (select a.*,b.button_count from(select shop.shop_id,shop.shop_name,shop.scan_times from SHOP shop)a left join\n" +
            " (select count(*) as button_count,shop_id from CUST_SHARE_INFO where to_char(share_time,'yyyy-mm-dd')>=#{startTime} and to_char(share_time,'yyyy-mm-dd')<=#{endTime} group by SHOP_ID)b   ON a.shop_id=b.shop_id)c left join\n" +
            " (select count(*) as share_count,shop_id from GET_POINT_RECORD where to_char(create_time,'yyyy-mm-dd')>=#{startTime} and to_char(create_time,'yyyy-mm-dd')<=#{endTime} group by SHOP_ID)d ON c.shop_id=d.shop_id")
    List<CouponCount> selectClickCount(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
