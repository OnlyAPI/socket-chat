package com.yf.chat.socket.result;

import lombok.Data;

@Data
public class Result<T> {

    private int code;

    private String msg;

    private T data;


    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> ok() {
        return ok(null);
    }

    public static <T> Result<T> ok(T data) {
        return new Result<>(0, "OK", data);
    }


    public static <T> Result<T> error() {
        return error(500, "error");
    }

    public static <T> Result<T> error(int code, String msg) {
        return error(code, msg, null);
    }

    public static <T> Result<T> error(int code, String msg, T data) {
        return new Result<>(code, msg, data);
    }


}
