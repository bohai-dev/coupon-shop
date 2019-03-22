package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.ShopNotice;
import com.qiaosheng.coupon.domain.SpecialDishes;
import com.qiaosheng.coupon.utils.CommonUtils;
import jdk.nashorn.internal.runtime.linker.LinkerCallSite;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by cxy on 2019/2/22
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DishesMapperTest {

    @Autowired
    SpecialDishesMapper dishesMapper;

    @Autowired
    ShopNoticeMapper shopNoticeMapper;

    @Test
    public void  insertListTest(){
        List<SpecialDishes> dishesList=new ArrayList<>();

        for (int i=0;i<3;i++){
           SpecialDishes specialDish=new SpecialDishes();
           specialDish.setDishId(dishesMapper.generateId());
           specialDish.setShopId("1111");
           specialDish.setIsDelete("0");
           specialDish.setImagePath("1.jpg");
           specialDish.setDishName("特色菜"+i);

           dishesList.add(specialDish);
        }

        dishesMapper.insertList(dishesList);
    }

    @Test
    public void  insertNoticeList(){

        List<ShopNotice> list=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            ShopNotice shopNotice=new ShopNotice();
            shopNotice.setNoticeId(CommonUtils.generateId());
            shopNotice.setNoticeContent("fefhuehfurhfr");
            shopNotice.setShopId("ffdfd");
            shopNotice.setIsDelete(1);
            shopNotice.setCreateTime(new Date());
            shopNotice.setUpdateTime(new Date());
            list.add(shopNotice);


        }
        int count=shopNoticeMapper.insertList(list);
        Assert.assertEquals(4L,count);


    }
}
