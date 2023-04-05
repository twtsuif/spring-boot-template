package com.suif.pojo.vo;

import lombok.Data;

@Data
public class UserLoginVO {
    private Long uid;
    private String username;
    private String token;
}
