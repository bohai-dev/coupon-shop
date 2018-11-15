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
 * Created by cxy on 2018/11/6
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
        //店铺编号,5位数
        String shopNo = CommonUtils.getShortNo(Integer.parseInt(shopId));
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
               coupon.setShopId(shopNo);
               coupon.setIsDelete("0");
               coupon.setCreateTime(new Date());
               coupon.setUpdateTime(new Date());

                //设置优惠券编号
                String couponId=couponMapper.generateCouponId();
                String couponNo= CommonUtils.getLongNo(Integer.parseInt(couponId));
                coupon.setCouponId(couponNo);

                //保存优惠券
                couponMapper.insertSelective(coupon);

            }
        }

        shopMapper.insertSelective(shop);


    }

    @Transactional
    @Override
    public List<Shop> selectByUserId(String userId) {
        List<Shop> shopList=shopMapper.selectByUserId(userId);
        for (Shop shop : shopList ){
            //设置优惠券list
            List<Coupon> couponList=couponMapper.selectByShopId(shop.getShopId());
            shop.setCouponList(couponList);
        }
        return shopList;
    }

    //更新店铺信息
    @Transactional
    @Override
    public void updateShop(Shop shop){
        String shopNo=shop.getShopId();

        //删除店铺原来的优惠券
        int deleteCount=couponMapper.deleteByShopId(shopNo);

        //添加优惠券列表
        List<Coupon> couponList=shop.getCouponList();
        if (couponList!=null){
            for (Coupon coupon:couponList){
                coupon.setShopId(shopNo);
                coupon.setIsDelete("0");
                coupon.setCreateTime(new Date());
                coupon.setUpdateTime(new Date());

                //设置优惠券编号
                String couponId=couponMapper.generateCouponId();
                String couponNo= CommonUtils.getLongNo(Integer.parseInt(couponId));
                coupon.setCouponId(couponNo);

                //保存优惠券
                couponMapper.insertSelective(coupon);

            }
        }

        shop.setUpdataTime(new Date());
        shopMapper.updateByPrimaryKeySelective(shop);

    }



    @Override
    public List<Shop> selectByConditions(Shop conditionShop) {
        List<Shop> shopList=shopMapper.selectConditions(conditionShop);
        for (Shop shop : shopList ){
            //设置优惠券list
            List<Coupon> couponList=couponMapper.selectByShopId(shop.getShopId());
            shop.setCouponList(couponList);
        }
        return shopList;
    }
}
