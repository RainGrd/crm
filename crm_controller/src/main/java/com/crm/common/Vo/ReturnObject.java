package com.crm.common.Vo;

import com.crm.common.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/25 10:23
 * FileName: PageBean
 * Description: 传输类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReturnObject implements Serializable {
    /**
     * 响应码 0---失败，1---成功
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 返回其他数据
     */
    private Object Data;

    public static ReturnObject success() {
        return new ReturnObject(Constants.PAGE_BEAN_CODE_SUCCESS, "成功!", null);
    }
    public static ReturnObject success(Object data) {
        return new ReturnObject(Constants.PAGE_BEAN_CODE_SUCCESS, "成功", data);
    }


    public static ReturnObject success(String message, Object data) {
        return new ReturnObject(Constants.PAGE_BEAN_CODE_SUCCESS, "成功", data);
    }

    public static ReturnObject error() {
        return new ReturnObject(Constants.PAGE_BEAN_CODE_FAIL, "系统忙，请稍后重试...", null);
    }

    public static ReturnObject error(String msg) {
        return new ReturnObject(Constants.PAGE_BEAN_CODE_FAIL, msg, null);
    }

}
