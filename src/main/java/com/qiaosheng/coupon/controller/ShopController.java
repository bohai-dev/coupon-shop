package com.qiaosheng.coupon.controller;

import com.github.pagehelper.util.StringUtil;
import com.qiaosheng.coupon.domain.Shop;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.service.ShopService;
import com.qiaosheng.coupon.vo.ResponseHeader;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @RequestMapping("/selectbyuserid")
    public ReturnBody<List<Shop>> selectByUserId(@RequestParam("userId") String userId) throws CouponException{
        if (StringUtil.isEmpty(userId)){
            throw new CouponException(ErrorConstant.USER_ID_REQUIRED);
        }
        ReturnBody<List<Shop>> returnBody=new ReturnBody<>();
        List<Shop> shopList=shopService.selectByUserId(userId);
        returnBody.setData(shopList);

        return  returnBody;
    }

}
