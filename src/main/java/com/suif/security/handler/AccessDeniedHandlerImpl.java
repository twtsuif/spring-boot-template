package com.suif.security.handler;

import com.alibaba.fastjson.JSON;
import com.suif.utils.Result;
import com.suif.utils.WebUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;



@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        Result result = Result.fail(HttpStatus.FORBIDDEN.value(), "无权限", null);
        String noRightResponse = JSON.toJSONString(result);
        WebUtil.renderString(response,noRightResponse);
    }
}
