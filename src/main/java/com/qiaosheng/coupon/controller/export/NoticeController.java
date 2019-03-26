package com.qiaosheng.coupon.controller.export;

import com.qiaosheng.coupon.domain.ShopNotice;
import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.service.NoticeService;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by cxy on 2019/3/22
 */
@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Autowired
    NoticeService noticeService;

    @PostMapping
    public ReturnBody<Integer> addNoticeList(@RequestBody @Valid List<ShopNotice> list){

        ReturnBody<Integer> returnBody=new ReturnBody<>();
        int count=noticeService.add(list);

        returnBody.setData(count);

        return returnBody;

    }

    @PutMapping
    public ReturnBody<Integer> updateNotice(@RequestBody @Valid ShopNotice shopNotice) throws CouponException{
        if (StringUtils.isEmpty(shopNotice.getNoticeId())){
            throw  new CouponException(ErrorConstant.NOTICE_ID_REQUIRED);
        }
        ReturnBody<Integer> returnBody=new ReturnBody<>();

        int count=noticeService.updateNotice(shopNotice);
        returnBody.setData(count);
        return returnBody;
    }

    @DeleteMapping("/{noticeId}")
    public ReturnBody<Integer> deleteNotice(@PathVariable String noticeId){
          ReturnBody<Integer> returnBody=new ReturnBody<>();
          int count=noticeService.deleteNotice(noticeId);

          returnBody.setData(count);
          return returnBody;
    }

    @GetMapping
    public ReturnBody<List<ShopNotice>> selectNotice(){
        ReturnBody<List<ShopNotice>> returnBody=new ReturnBody<>();
        List<ShopNotice> list=noticeService.selectAll();
        returnBody.setData(list);

        return returnBody;
    }

}
