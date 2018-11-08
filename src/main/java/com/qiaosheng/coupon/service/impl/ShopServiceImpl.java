package com.qiaosheng.coupon.service.impl;

import com.qiaosheng.coupon.dao.CouponMapper;
import com.qiaosheng.coupon.dao.ShopMapper;
import com.qiaosheng.coupon.domain.Coupon;
import com.qiaosheng.coupon.domain.Shop;
import com.qiaosheng.coupon.service.ShopService;
import com.qiaosheng.coupon.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Cteated by cxy on 2018/11/6
 */
@Service
public class ShopServiceImpl  implements ShopService {

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    CouponMapper couponMapper;

    @Override
    @Transactional
    public void add(Shop shop) {
        String shopId = shopMapper.generateUserId();
        String shopNo = CommonUtils.getNo(Integer.parseInt(shopId));
        //设置店铺编号
        shop.setShopId(shopNo);
        //设置是否删除
        shop.setIsDelete("0");
        shop.setCreateTime(new Date());
        shop.setUpdataTime(new Date());

        //添加优惠券列表
        List<Coupon> couponList=shop.getCouponList();
        if (couponList!=null){
            for (Coupon coupon:couponList){
               coupon.setShopId(shopId);
               coupon.setIsDelete("0");
               coupon.setCreateTime(new Date());
               coupon.setUpdateTime(new Date());

                //设置优惠券编号
                String couponId=couponMapper.generateUserId();
                String couponNo= CommonUtils.getNo(Integer.parseInt(couponId));
                coupon.setCouponId(couponNo);

                //保存优惠券
                couponMapper.insertSelective(coupon);

            }
        }

        shopMapper.insertSelective(shop);


    }


}
