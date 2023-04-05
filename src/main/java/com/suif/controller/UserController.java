package com.suif.controller;

import com.suif.pojo.entity.User;
import com.suif.service.UserService;
import com.suif.utils.Result;
import com.suif.utils.SecurityUtil;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Resource
    UserService userService;

    @Resource
    SecurityUtil securityUtil;

    @GetMapping
    public Result getUsers(){
        Long uid = securityUtil.currentId();
        System.out.println(uid + "用户访问");
        List<User> allUser = userService.findAllUser();
        return Result.success(allUser);
    }
}
