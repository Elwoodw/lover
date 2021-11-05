package org.lover.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.core.util.IdUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;


    //设置过期时间
    public String generateToken(Map<String, Object> params) {
        JWT jwt = JWT.create()
                .setIssuedAt(DateUtil.date())
                .setNotBefore(DateUtil.date())
                .setExpiresAt(DateUtil.date(System.currentTimeMillis() + expiration))
                .setKey(secret.getBytes());
        //设置负载
        params.forEach((k, v) -> {
            jwt.setPayload(k, v);
        });

        return jwt.sign();

    }

    //验证token,算法和过期时间
    public boolean isValidToken(String token) {
        boolean isValidToken = JWTUtil.verify(token, secret.getBytes());
        if (!isValidToken) {
            log.warn("JWT Token 错误");
            return false;
        }
        try {
            JWTValidator.of(token).validateAlgorithm(JWTSignerUtil.hs256(secret.getBytes()));
        } catch (ValidateException e) {
            log.warn("JWT 算法错误");
            return false;
        }

        try {
            JWTValidator.of(token).validateDate(DateUtil.date());
        } catch (ValidateException e) {
            log.warn("JWT Token 过期");
            return false;
        }
        return true;
    }


    public Object getPayLoad(String token, String payloadName) {
        return JWTUtil.parseToken(token).getPayload(payloadName);
    }


}
