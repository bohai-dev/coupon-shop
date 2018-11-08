package com.qiaosheng.coupon.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.qiaosheng.coupon.domain.Coupon;
import com.qiaosheng.coupon.domain.Shop;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Cteated by cxy on 2018/11/7
 */
public class CommonUtilsTest {

    @Test
    public void  getJsonStr(){

        Shop shop=new Shop();
        List<Coupon> couponList=new ArrayList<>();
        couponList.add(new Coupon());
        shop.setCouponList(couponList);
        String jsonStr=JSON.toJSONString(shop,SerializerFeature.WriteMapNullValue);

        System.out.println("生成的json字符串:"+jsonStr);

    }
}
