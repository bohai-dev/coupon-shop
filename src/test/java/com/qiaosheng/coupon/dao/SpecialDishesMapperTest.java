package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.SpecialDishes;
import com.qiaosheng.coupon.vo.CouponCount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxy on 2018/12/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpecialDishesMapperTest {

    @Autowired
    SpecialDishesMapper dishesMapper;

    @Autowired
    CouponCountMapper countMapper;

    @Test
    public void couponCountTest(){
        List<CouponCount> list=countMapper.selectCount("2019-02-01","2019-02-12");
        System.out.println(list.size());
    }




}
