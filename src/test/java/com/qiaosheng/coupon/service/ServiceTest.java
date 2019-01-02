package com.qiaosheng.coupon.service;

import com.qiaosheng.coupon.dao.SpecialDishesMapper;
import com.qiaosheng.coupon.domain.SpecialDishes;
import com.qiaosheng.coupon.service.taskscheduler.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cxy on 2018/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    TokenService tokenService;

    @Autowired
    SpecialDishesMapper dishesMapper;


    @Test
    public void getTokenTest(){

    }

    @Test
    @Transactional
    public void  insertListTest(){
        //添加招牌菜品
        List<SpecialDishes> specialDishes=new ArrayList<>();


            for (int i=0;i<3;i++){
                SpecialDishes dish=new SpecialDishes();
                dish.setDishId(dishesMapper.generateId());
                dish.setShopId("123");
                dish.setIsDelete("0");
                dish.setBackColumn1("");
                dish.setBackColumn2("");

                //dishesMapper.insertSelective(dish);
            }

            dishesMapper.insertList(specialDishes);   //mybatis缓存导致每次都生成相同的序列id






    }



}
