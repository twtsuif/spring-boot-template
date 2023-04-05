package com.suif.controller;

import com.suif.pojo.param.LoginParam;
import com.suif.pojo.vo.UserLoginVO;
import com.suif.service.AuthService;
import com.suif.utils.Result;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    @Resource
    AuthService authService;

    @PostMapping
    public Result login(@RequestBody LoginParam param){
        UserLoginVO userLoginVO = authService.auth(param.getUsername(), param.getPassword());
        return Result.success(userLoginVO);
    }
}
