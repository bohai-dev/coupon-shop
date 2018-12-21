package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.SpecialDishes;
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
    GlobalMapper globalMapper;
    @Test
    public void insertListTest(){
        List<SpecialDishes> list=new ArrayList<>();
        for (int i=0;i<5;i++){
            SpecialDishes specialDishes=new SpecialDishes();
            specialDishes.setShopId("5454");
            specialDishes.setBackColumn1("back");
            specialDishes.setBackColumn2("back");
            specialDishes.setDishName("特色");
            specialDishes.setDishPrice("12.5");
            specialDishes.setImagePath("dfdfd");
            specialDishes.setIsDelete("0");
          //  specialDishes.setDishId(i+"12");
            list.add(specialDishes);
        }

        list.forEach(dish->{

             dish.setDishId(globalMapper.generateId());
             dish.setBackColumn1("");
             dish.setBackColumn2("");
        });

        int count=dishesMapper.insertList(list);
    }
}
