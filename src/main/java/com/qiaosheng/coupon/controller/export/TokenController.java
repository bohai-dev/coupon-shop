package com.qiaosheng.coupon.controller.export;

import com.qiaosheng.coupon.service.taskscheduler.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cxy on 2019/1/9
 */
@RestController
public class TokenController {
    @Autowired
    TokenService tokenService;

    @GetMapping("/access_token")
    public String getToken(){
        String token=tokenService.getToken();
        return token;
    }
}
