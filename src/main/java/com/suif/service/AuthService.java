package com.suif.service;

import com.suif.pojo.entity.User;
import com.suif.pojo.vo.UserLoginVO;
import com.suif.repository.UserRepository;
import com.suif.security.SecurityUser;
import com.suif.utils.JwtUtil;
import com.suif.utils.RedisUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Resource
    UserRepository userRepository;

    @Resource
    RedisUtil redisUtil;

    @Resource
    AuthenticationManager authenticationManager;

    private static final String REDIS_LOGIN_KEY = "login_";


    public UserLoginVO auth(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);

        // 给Security认证 执行UserDetailsService的重载方法
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        // 此处已认证完成
        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        UserLoginVO userLoginVO = securityUserToUserLoginVO(securityUser.getUser());

        // 生成Token
        String userId = user.getId().toString();
        String JWT = JwtUtil.createJWT(userId);
        userLoginVO.setToken(JWT);

        // 放入缓存
        redisUtil.set(REDIS_LOGIN_KEY + userId, securityUser);

        return userLoginVO;
    }

    private UserLoginVO securityUserToUserLoginVO(User user) {
        UserLoginVO userLoginVO = new UserLoginVO();
        BeanUtils.copyProperties(user, userLoginVO);
        userLoginVO.setUid(user.getId());

        return userLoginVO;
    }

}
