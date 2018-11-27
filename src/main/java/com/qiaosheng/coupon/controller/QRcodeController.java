package com.qiaosheng.coupon.controller;

import com.alibaba.fastjson.JSONObject;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by cxy on 2018/11/23
 */
@RestController
@RequestMapping("/qrcode")
@CrossOrigin
public class QRcodeController {

    @RequestMapping("/geturl")
    public ReturnBody<String>  getUrl(@RequestParam("shopId")String shopId,@RequestParam("width")Integer width){

        ReturnBody<String> returnBody=new ReturnBody<>();

        /*JSONObject jsonObject=new JSONObject();
        jsonObject.put("author","Creativefun Technology");
        jsonObject.put("shopId",shopId);*/

        String data="shopId="+shopId+";author="+"Creativefun Technology";
        StringBuilder codeBuilder=new StringBuilder("http://mobile.qq.com/qrcode/?");
        codeBuilder.append("width="+width+"&height="+width);
        codeBuilder.append("&url="+data);


        String codeUrl=codeBuilder.toString();

        returnBody.setData(codeUrl);

        return returnBody;




    }
}
