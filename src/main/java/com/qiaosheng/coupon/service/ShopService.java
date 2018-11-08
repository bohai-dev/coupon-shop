package com.qiaosheng.coupon.service;

import com.qiaosheng.coupon.domain.Shop;

import java.util.List;

/**
 * Cteated by cxy on 2018/11/6
 */
public interface ShopService {

    void add(Shop shop);
    List<Shop> selectByUserId(String userId);
    void updateShop(Shop shop);
}
