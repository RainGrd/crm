package com.crm.exception;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/27 18:41
 * FileName: SystemException
 * Description:系统异常
 */
public class SystemException extends Exception {
    /**
     * 异常信息
     */
    private String message;
    /**
     * 异常错误码
     */
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SystemException() {

    }

    public SystemException(String message, Throwable cause, String message1, Integer code) {
        super(message, cause);
        this.message = message1;
        this.code = code;
    }

}
