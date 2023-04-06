package com.kitchen.common.core.page.response;


import java.util.Objects;

/**
 * @Author julyWhj
 * @Description 返回通用对象$
 * @Date 2021/9/23 10:07 上午
 **/
public class ObjResponse<T> extends BaseResponse<T> {
    private Integer statusCode;

    public void add(T t) {
        data = t;
    }

    @Override
    public T getData() {
        return data;
    }

    public static <T> ObjResponse<T> of(T data) {
        ObjResponse<T> response = new ObjResponse<>();
        response.add(data);
        response.setStatusCode(SUCCESS);
        response.setCode(SUCCESS);
        return response;
    }

    public static <T> ObjResponse<T> ofError(String msg) {
        ObjResponse<T> response = new ObjResponse<>();
        response.setFail(msg);
        response.setStatusCode(FAIL);
        response.setCode(FAIL);
        return response;
    }

    public static <T> ObjResponse<T> ofError(String msg,T t) {
        ObjResponse<T> response = new ObjResponse<>();
        response.setFail(msg);
        response.setData(t);
        response.setStatusCode(FAIL);
        response.setCode(FAIL);
        return response;
    }

    /**
     * 远程数据不存在
     *
     * @param <T> 范形
     * @return
     */
    public static <T> ObjResponse<T> nonExistent() {
        ObjResponse<T> response = new ObjResponse<>();
        response.setMessage("数据不存在");
        response.setStatusCode(NON_EXISTENT);
        response.setCode(NON_EXISTENT);
        return response;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
