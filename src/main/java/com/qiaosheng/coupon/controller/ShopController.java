package com.qiaosheng.coupon.controller;

import com.qiaosheng.coupon.domain.Shop;
import com.qiaosheng.coupon.service.ShopService;
import com.qiaosheng.coupon.vo.ResponseHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Cteated by cxy on 2018/11/7
 */
@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/add")
    public ResponseHeader add(@RequestBody @Valid Shop shop){

        ResponseHeader responseHeader=new ResponseHeader();
        shopService.add(shop);

        return responseHeader;

    }
}
