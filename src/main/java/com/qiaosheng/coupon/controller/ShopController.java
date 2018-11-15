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
 * Created by cxy on 2018/11/7
 */
@CrossOrigin
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopService shopService;

    @RequestMapping("/add")
    public ResponseHeader addShop(@RequestBody @Valid Shop shop){

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

    /**
     * 更新店铺信息
     * @param shop
     * @return
     * @throws CouponException
     */
    @RequestMapping("/updateshop")
    public ResponseHeader updateShop(@RequestBody @Valid Shop shop) throws CouponException{
        if (StringUtil.isEmpty(shop.getShopId())){
            throw new CouponException(ErrorConstant.SHOP_ID_REQUIRED);
        }

        ResponseHeader responseHeader=new ResponseHeader();
        shopService.updateShop(shop);
        return  responseHeader;
    }
    @RequestMapping("/selectbyconditions")
    public ReturnBody<List<Shop>> selectByConditions(@RequestBody Shop conditionShop) throws CouponException{
        if (null==conditionShop){
            throw new CouponException(ErrorConstant.SELECT_CONDITIONS_REQUIRED);
        }
        List<Shop> shopList=shopService.selectByConditions(conditionShop);
        ReturnBody<List<Shop>> returnBody=new ReturnBody<>();
        returnBody.setData(shopList);

        return returnBody;

    }

}
