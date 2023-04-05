package com.suif.utils;

import com.suif.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private Integer status;
    private String message;
    private Object data;

    public static Result success(Object data) {
        return new Result(200, "success", data);
    }

    public static Result fail(Integer status, String message, Object data) {
        return new Result(status, message, data);
    }

    public static Result fail(String message) {
        return fail(400, message, null);
    }

    public static Result fail(Integer status, String message) {
        return fail(status, message, null);
    }

    public static Result fail(ErrorCode errorCode) {
        return fail(errorCode.getStatus(), errorCode.getMessage());
    }
}
