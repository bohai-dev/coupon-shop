package com.qiaosheng.coupon.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Cteated by cxy on 2018/11/12
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //super.addInterceptors(registry);
      /*  List<String> list=new ArrayList<>();
        list.add("/login");
        list.add("/register");
        registry.addInterceptor(new JwtInterceptor()).excludePathPatterns(list);*/
    }
}
