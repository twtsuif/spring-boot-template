package com.suif.enums;

import lombok.Getter;

@Getter
public enum ErrorCode {
    EXIST_USERNAME (10001,"用户名已存在"),

    INVALID_PARAM (20001,"参数非法"),
    DATA_NOTFOUND("未找到数据");

    private final Integer status;
    private final String message;


    // 内部计数器 递增方式创建枚举 go语言enum关键字
    ErrorCode(Integer status, String message){
        this.status=status;
        this.message = message;
        Counter.nextCode = status + 1;
    }

    ErrorCode(String message){
        this(Counter.nextCode, message);
    }

    private static class Counter{
        private static int nextCode = 0;
    }
}
