package com.qiaosheng.coupon.controller.export;

import com.qiaosheng.coupon.dao.CouponCountMapper;
import com.qiaosheng.coupon.vo.CouponCount;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;
import java.util.List;

/**
 * Created by cxy on 2019/1/2
 */
@RestController
@CrossOrigin
public class StatisticsController {

    @Autowired
    CouponCountMapper countMapper;

    @RequestMapping("/statistics")
    public ReturnBody<List<CouponCount>> couponStatistics(){
        ReturnBody<List<CouponCount>> returnBody=new ReturnBody<>();
        List<CouponCount> list=countMapper.selectCount();

        returnBody.setData(list);

        return returnBody;

    }

    @GetMapping("/statistics/clickcount")
    public ReturnBody<List<CouponCount>> clickCount(){
        ReturnBody<List<CouponCount>> returnBody=new ReturnBody<>();
        List<CouponCount> list=countMapper.selectClickCount();

        returnBody.setData(list);

        return returnBody;
    }
}
