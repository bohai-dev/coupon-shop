package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.Coupon;
import com.qiaosheng.coupon.domain.Shop;
import com.qiaosheng.coupon.domain.SpecialDishes;
import com.qiaosheng.coupon.utils.CommonUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by cxy on 2018/11/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopMapperTest {

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    CouponMapper couponMapper;


    @Test
    public void generateUserIdTest(){

        String id=shopMapper.generateUserId();
        String number=CommonUtils.getShortNo(Integer.parseInt(id));
        System.out.println(id);
    }

    @Test
    public void addCouponTest(){
        Coupon coupon=new Coupon();
       // coupon.setCouponId("00090");
        coupon.setShopId("shop00001");
        coupon.setCouponType("1");
        coupon.setCouponValue("50");
        coupon.setExchangeTimes(60L);
        coupon.setScanTimes(100);

        couponMapper.insertSelective(coupon);
    }

    @Test
    public void deleteCoupon(){
        String shopId="00007";
        int count=couponMapper.deleteByShopId(shopId);
        Assert.assertEquals((long)count,1L);
    }
    @Test
    public void isMobile(){
        String str="18305626606";

        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        System.out.println("验证结果:"+b);


    }

    @Test
    public void generateIdTest(){
        List<SpecialDishes> list=new ArrayList<>();

        for (int i = 0; i <5 ; i++) {
            //String id=shopMapper.generateUserId();
           // System.out.println(id);
            SpecialDishes specialDishes=new SpecialDishes();
            specialDishes.setDishName("特色"+i);
            list.add(specialDishes);
        }
        for (SpecialDishes dishes: list){
            dishes.setDishId(shopMapper.generateUserId());
        }
        System.out.println(list);

    }



}
