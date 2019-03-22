package com.qiaosheng.coupon.controller.export;

import com.qiaosheng.coupon.dao.GetPointRecordMapper;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.utils.HttpUtil;
import com.qiaosheng.coupon.vo.ResponseHeader;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cxy on 2018/12/20
 */
@CrossOrigin
@RestController
@RequestMapping("/pointrecord")
public class GetPointRecordController {

    @Autowired
    GetPointRecordMapper pointRecordMapper;


    @GetMapping("/resetstatus")
    public ResponseHeader resetStatus() throws CouponException{


        ResponseHeader responseHeader=new ResponseHeader();
         //转发状态重置
        int count=pointRecordMapper.resetStatus();
        //长图保存状态重置
       /* String shareUrl="https://www.95cfun.cn/custShare/updShareStatue?shareStyle=05";
        try {
            String result=HttpUtil.get(shareUrl);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CouponException(ErrorConstant.UNKNOW_EXCEPTION);
        }*/

       return responseHeader;
    }


}
