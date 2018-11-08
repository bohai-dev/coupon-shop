package com.qiaosheng.coupon.controller;

import com.qiaosheng.coupon.domain.AdminUser;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.service.AdminUserService;
import com.qiaosheng.coupon.vo.ResponseHeader;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Cteated by cxy on 2018/11/6
 */
@CrossOrigin
@RestController
public class AdminUserController {

    @Autowired
    AdminUserService userService;


    @RequestMapping("/register")
    public ResponseHeader register(@RequestBody @Valid AdminUser adminUser) throws CouponException {

        ResponseHeader responseHeader=new ResponseHeader();
        userService.addUser(adminUser);

        return responseHeader;


    }

    @RequestMapping("/login")
    public ReturnBody<AdminUser> login(@RequestBody @Valid AdminUser adminUser) throws CouponException{
        ReturnBody<AdminUser> returnBody=new ReturnBody<>();
        AdminUser resultUser=userService.selectUser(adminUser);
        returnBody.setData(resultUser);

        return  returnBody;
    }



}
