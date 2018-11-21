package com.qiaosheng.coupon.controller;

import com.qiaosheng.coupon.domain.AdminUser;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.service.AdminUserService;
import com.qiaosheng.coupon.utils.JWTUtil;
import com.qiaosheng.coupon.vo.ResponseHeader;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxy on 2018/11/6
 */
@CrossOrigin
@Controller
public class AdminUserController {

    @Autowired
    AdminUserService userService;

    @ResponseBody
    @RequestMapping("/register")
    public ResponseHeader register(@RequestBody @Valid AdminUser adminUser) throws CouponException {

        ResponseHeader responseHeader=new ResponseHeader();
        userService.addUser(adminUser);

        return responseHeader;


    }

    @ResponseBody
    @RequestMapping("/login")
    public ReturnBody<Map<String,String>> login(@RequestBody @Valid AdminUser adminUser) throws CouponException{

        ReturnBody<Map<String,String>> returnBody=new ReturnBody<>();

        AdminUser resultUser=userService.selectUser(adminUser);

       // returnBody.setData(resultUser);
        if (resultUser==null){
            throw new CouponException(ErrorConstant.USER_NOT_EXISTS);
        }

         String token=JWTUtil.getToken(resultUser.getUserId());
         String userName=resultUser.getUserName();
         String userId=resultUser.getUserId();

         Map<String,String>  map=new HashMap<>();
         map.put("token",token);
         map.put("userName",userName);
         map.put("userId",userId);
         returnBody.setData(map);


        return  returnBody;
    }



}
