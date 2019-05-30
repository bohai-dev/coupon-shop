package com.qiaosheng.coupon.service.taskscheduler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qiaosheng.coupon.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Created by cxy on 2018/11/28
 */
@Service
public class TokenService {

    private static final String  APPID="wx9f9e25b68ad1c5be";
    private static final String  SECRET="96aad7edf71938c44a3ce94d0001fdf9";
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenService.class);

    private String token;


    @Scheduled(fixedRate = 2*50*60*1000)  //  100分钟刷新一次
    public void  obtainToken(){
        StringBuilder tokenUrl=new StringBuilder("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential");
        tokenUrl.append("&appid=").append(APPID).append("&secret=").append(SECRET);

        try {
            String result=HttpUtil.get(tokenUrl.toString());
            LOGGER.info("token result="+result);

            JSONObject jsonObject=JSON.parseObject(result);
            String errorCode=jsonObject.getString("errcode");
            if (StringUtils.isEmpty(errorCode)){
                String token=jsonObject.getString("access_token");   //7200秒过期
                this.token=token;
            }else {
                //获取错误信息
                String errorMsg=jsonObject.getString("errmsg");
                LOGGER.error("获取token错误:{"+errorCode+","+errorMsg+"}");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public String getToken() {
        return token;
    }
}
