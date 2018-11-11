package com.qiaosheng.coupon.controller;
import com.github.pagehelper.util.StringUtil;
import com.qiaosheng.coupon.domain.Coupon;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.service.CouponService;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    CouponService couponService;

    /**  根据id查询优惠券信息
     * @param couponId 优惠券id
     * @return
     * @throws CouponException
     */
    @RequestMapping("/selectbyid")
    public ReturnBody<Coupon> selectById(@RequestParam("couponId") String couponId) throws CouponException{
        if (StringUtil.isEmpty(couponId)){
            throw new CouponException(ErrorConstant.COUPON_ID_REQUIRED);
        }
        ReturnBody<Coupon> returnBody=new ReturnBody<>();
        Coupon coupon=couponService.selectById(couponId);
        returnBody.setData(coupon);
        return returnBody;
    }


}
