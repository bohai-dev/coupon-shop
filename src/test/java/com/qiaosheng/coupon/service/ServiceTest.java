package com.qiaosheng.coupon.service;

import com.qiaosheng.coupon.service.taskscheduler.TokenService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by cxy on 2018/11/28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

    @Autowired
    TokenService tokenService;


    @Test
    public void getTokenTest(){

    }



}
