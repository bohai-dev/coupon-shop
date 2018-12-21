package com.qiaosheng.coupon.controller.export;

import com.qiaosheng.coupon.dao.GetPointRecordMapper;
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
    public ResponseHeader resetStatus(){
       int count=pointRecordMapper.resetStatus();

       ResponseHeader responseHeader=new ResponseHeader();

       return responseHeader;
    }


}
