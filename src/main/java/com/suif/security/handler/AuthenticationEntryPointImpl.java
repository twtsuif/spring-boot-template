package com.suif.security.handler;

import com.alibaba.fastjson.JSON;
import com.suif.utils.Result;
import com.suif.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        Result result = Result.fail(HttpStatus.UNAUTHORIZED.value(), "认证失败", null);
        String json = JSON.toJSONString(result);
        WebUtil.renderString(response,json);
    }
}
