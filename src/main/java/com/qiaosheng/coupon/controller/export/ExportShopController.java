package com.qiaosheng.coupon.controller.export;

import com.qiaosheng.coupon.domain.Shop;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.service.ShopService;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by cxy on 2018/11/15
 */
@CrossOrigin
@RestController
@RequestMapping("/export/shop")
public class ExportShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/selectbyconditions")
    public ReturnBody<List<Shop>> selectByConditions(@RequestBody Shop conditionShop) throws CouponException {
        if (null==conditionShop){
            throw new CouponException(ErrorConstant.SELECT_CONDITIONS_REQUIRED);
        }
        List<Shop> shopList=shopService.selectByConditions(conditionShop);
        ReturnBody<List<Shop>> returnBody=new ReturnBody<>();
        returnBody.setData(shopList);

        return returnBody;

    }
}
