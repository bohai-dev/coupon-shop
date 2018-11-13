package com.qiaosheng.coupon.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Cteated by cxy on 2018/11/12
 */
public class JWTUtil {

    final static String base64EncodedSecretKey = "couponHS256coupon";//私钥
    final static long TOKEN_EXP = 1000 * 60*30;//过期时间,测试使用60秒

    public static String getToken(String userId) {
        return Jwts.builder()
                .setSubject(userId)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXP)) /*过期时间*/
                .signWith(SignatureAlgorithm.HS256, base64EncodedSecretKey)
                .compact();
    }

    /**
     * @Date:17-12-12 下午6:21
     * @Author:root
     * @Desc:检查token,只要不正确就会抛出异常
     **/
    public static void checkToken(String token) throws Exception{
        try {
            final Claims claims = Jwts.parser().setSigningKey(base64EncodedSecretKey).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e1) {
            throw new Exception("token expired");
        } catch (Exception e) {
            throw new Exception("invalid token");
        }



    }


}
