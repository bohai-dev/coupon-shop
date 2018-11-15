package com.qiaosheng.coupon.service;

import com.qiaosheng.coupon.domain.AdminUser;
import com.qiaosheng.coupon.exception.CouponException;

/**
 * Created by cxy on 2018/11/6
 */
public interface AdminUserService {
    //新增用户
    void addUser(AdminUser adminUser)  throws CouponException;

    //查询用户
    AdminUser selectUser(AdminUser adminUser) throws CouponException;

}