package com.qiaosheng.coupon.service.impl;

import com.qiaosheng.coupon.dao.AdminUserMapper;
import com.qiaosheng.coupon.domain.AdminUser;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Cteated by cxy on 2018/11/6
 */
@Service
public class AdminUserServiceImpl  implements AdminUserService {

    @Autowired
    AdminUserMapper userMapper;

    @Override
    public void addUser(AdminUser adminUser) throws CouponException{
        AdminUser user=userMapper.selectByUserName(adminUser.getUserName());

        if (user!=null){
            throw new CouponException(ErrorConstant.USER_NAME_EXISTS);
        }

        String userId=userMapper.generateUserId();
        adminUser.setUserId(userId);
        userMapper.insertSelective(adminUser);

    }


    @Override
    public AdminUser selectUser(AdminUser adminUser) throws CouponException{

        AdminUser user=userMapper.selectByNamePwd(adminUser.getUserName(),adminUser.getUserPwd());
        if (user==null){
           throw new CouponException(ErrorConstant.USER_NOT_EXISTS);
        }
        return user;

    }
}
