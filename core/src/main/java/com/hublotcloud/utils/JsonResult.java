package com.hublotcloud.utils;

import static com.hublotcloud.Constants.INT_200;
import static com.hublotcloud.Constants.INT_404;
import static com.hublotcloud.Constants.INT_500;

/**
 * JsonResult
 */
public class JsonResult<T> {

    private int code;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static final <T> JsonResult<T> jsonResultSuccess(String message, T t) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(INT_200);
        jsonResult.setMessage(message);
        jsonResult.setData(t);
        return jsonResult;
    }

    public static final <T> JsonResult<T> jsonResultFail(String message) {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(INT_500);
        jsonResult.setMessage(message);
        return jsonResult;
    }

    public static final <T> JsonResult<T> jsonResultNotFound() {
        JsonResult<T> jsonResult = new JsonResult<T>();
        jsonResult.setCode(INT_404);
        return jsonResult;
    }

}
