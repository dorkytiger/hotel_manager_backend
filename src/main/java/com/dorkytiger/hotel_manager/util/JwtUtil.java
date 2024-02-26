package com.dorkytiger.hotel_manager.util;

import com.dorkytiger.hotel_manager.model.user.UserInfoEntity;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    /**
     * 密钥
     */
    private static final String SECRET = "my_secret";

    /**
     * 过期时间
     **/
    private static final long EXPIRATION = 12*60*60L;//单位为秒

    /**
     * 生成用户token,设置token超时时间
     */
    public static String createToken(UserInfoEntity userInfoEntity) {
        //设置载荷
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userInfoEntity.getId());
        claims.put("username", userInfoEntity.getUsername());
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)//设置签名(头）
                .setClaims(claims)//设置载荷（身体）
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))//有效期
                .compact();
    }

    /**
     * 校验token并解析token
     */
    public static Claims verifyToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

    }

}