package com.qiaosheng.coupon.controller.export;

import com.qiaosheng.coupon.dao.CouponCountMapper;
import com.qiaosheng.coupon.vo.CouponCount;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ReturnBody<List<CouponCount>> couponStatistics(@RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime){
        ReturnBody<List<CouponCount>> returnBody=new ReturnBody<>();
        List<CouponCount> list=countMapper.selectCount(startTime, endTime);

        returnBody.setData(list);

        return returnBody;

    }

    @GetMapping("/statistics/clickcount")
    public ReturnBody<List<CouponCount>> clickCount(@RequestParam("startTime")String startTime, @RequestParam("endTime")String endTime){
        ReturnBody<List<CouponCount>> returnBody=new ReturnBody<>();
        List<CouponCount> list=countMapper.selectClickCount(startTime,endTime);

        returnBody.setData(list);

        return returnBody;
    }
}
