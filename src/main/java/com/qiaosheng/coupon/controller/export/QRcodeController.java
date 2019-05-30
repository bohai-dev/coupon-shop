package com.qiaosheng.coupon.controller.export;

import com.alibaba.fastjson.JSONObject;
import com.qiaosheng.coupon.service.taskscheduler.TokenService;
import com.qiaosheng.coupon.utils.HttpUtil;
import com.qiaosheng.coupon.vo.ReturnBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by cxy on 2018/11/23
 */
@RestController
@RequestMapping("/qrcode")
@CrossOrigin
public class QRcodeController {

    @Autowired
    TokenService tokenService;

    String token="21_SBVFj8OnCgeCKiWrl2HU-HEBtTRfX2cgbTrfE8r6ERIKb88TNu9c-xYXoWQMc2NeVSdMLkWnWFaBdcBU7vPU3EOJvyXIwEr00Cpp-wQvflnPtF2ULG4ih18zS67rdOsUto0Fo90J3tw32QdYKLMhAEABCD";

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

    @RequestMapping("/getwxcode")
    public void getWxQRcode(@RequestParam("shopId") String shopId, @RequestParam(value = "width",required = false,defaultValue = "200") String width, HttpServletResponse httpServletResponse) {
        String token=this.token;
        System.out.println("取得token:"+token);
        String codeUrl="https://api.weixin.qq.com/wxa/getwxacode?access_token="+token;
        Map<String,String> params=new HashMap<>();
        params.put("path","pages/storeDetail/storeDetail?params="+shopId);
        params.put("width",width);

        try {
            InputStream imageStream= HttpUtil.postStream(codeUrl,params);
            if (imageStream!=null){
                httpServletResponse.setContentType("image/jpg");
                OutputStream os = httpServletResponse.getOutputStream();
                int length = 0;
                byte[] buf = new byte[1024];

                while ((length = imageStream.read(buf)) > 0) {
                    os.write(buf, 0, length);
                }
                os.flush();
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }




}
