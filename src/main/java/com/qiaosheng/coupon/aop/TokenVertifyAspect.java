package com.qiaosheng.coupon.aop;

import com.qiaosheng.coupon.exception.CouponException;
import com.qiaosheng.coupon.exception.ErrorConstant;
import com.qiaosheng.coupon.utils.JWTUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by cxy on 2018/11/13
 * token 验证切面
 */
//@Component
@Aspect
@Order(1)   //值越小，优先级越高
public class TokenVertifyAspect {
    
    /** logger */
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenVertifyAspect.class);

    @Pointcut("execution(public * com.qiaosheng.coupon.controller.*.*(..)) && !execution(public * com.qiaosheng.coupon.controller.AdminUserController.*(..))")
    public void tokenVerify(){}

    @Before("tokenVerify()")
    public void  doBefore(JoinPoint joinPoint) throws CouponException {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        String authHeader = request.getHeader("Authorization");

        LOGGER.info("token验证:"+authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer:")) {
            //  throw new ServletException("invalid Authorization header");
            throw new CouponException(ErrorConstant.LOGIN_CHECK_ERROR.getErrorCode(),"invalid Authorization header");
        }


        //取得token
        String token = authHeader.substring(7);
        try {
            JWTUtil.checkToken(token);
        } catch (Exception e) {
            throw new CouponException(ErrorConstant.LOGIN_CHECK_ERROR.getErrorCode(),e.getMessage());
        }

    }
}
