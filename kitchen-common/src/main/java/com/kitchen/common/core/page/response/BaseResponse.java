package com.kitchen.common.core.page.response;


import java.io.Serializable;

/**
 * @author: wanghongjie
 * @date: 2023-04-04
 * @verison: 1.0
 */
public class BaseResponse<T> implements Serializable {

    public static final Integer SUCCESS = 200;

    public static final Integer FAIL = 500;
    public static final Integer NON_EXISTENT = 501;


    private int code;


    private String message;


    protected T data;

    public void setFail(String message) {
        setCode(FAIL);
        setMessage(message);
    }

    public BaseResponse() {
        super();
        this.code = SUCCESS;
    }

    public BaseResponse(Integer result, String message) {
        super();
        this.code = result;
        this.message = message;
    }

    public boolean success() {
        return SUCCESS.equals(this.code);
    }

    public boolean failure() {
        return FAIL.equals(this.code);
    }

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
}
