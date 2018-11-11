package com.qiaosheng.coupon.service.impl;

import com.qiaosheng.coupon.dao.CouponMapper;
import com.qiaosheng.coupon.domain.Coupon;
import com.qiaosheng.coupon.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    CouponMapper couponMapper;

    @Override
    public Coupon selectById(String couponId) {
        return couponMapper.selectByPrimaryKey(couponId);
    }


}
