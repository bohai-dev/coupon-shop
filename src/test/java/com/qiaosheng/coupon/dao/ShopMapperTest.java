package com.qiaosheng.coupon.dao;

import com.qiaosheng.coupon.domain.Shop;
import com.qiaosheng.coupon.utils.CommonUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cteated by cxy on 2018/11/7
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopMapperTest {

    @Autowired
    ShopMapper shopMapper;

    @Test
    public void generateUserIdTest(){

        String id=shopMapper.generateUserId();
        String number=CommonUtils.getNo(Integer.parseInt(id));
        System.out.println(id);
    }

    @Test
    public void isMobile(){
        String str="18305626606";

        Pattern p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        Matcher m = p.matcher(str);
        boolean b = m.matches();
        System.out.println("验证结果:"+b);


    }



}
