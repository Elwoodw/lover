package org.lover.controller;

import cn.hutool.core.util.StrUtil;
import org.lover.exception.BizException;
import org.lover.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/getCurrentUser")
    public Object getCurrentUser(Authentication authentication, HttpServletRequest request)
    {
        String header=request.getHeader("Authorization");
        String token= StrUtil.subAfter(header,"bearer",false);
        if(jwtTokenUtil.isValidToken(token)) {
            return jwtTokenUtil.parseToken(token);
        }
        else
        {
            throw  new BizException("Token 错误");
        }
    }
}
