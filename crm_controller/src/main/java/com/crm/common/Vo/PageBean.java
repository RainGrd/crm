package com.crm.common.Vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/25 10:23
 * FileName: PageBean
 * Description: 传输类
 */
@Data
public class PageBean implements Serializable {
    /**
     * 响应码 0---失败，1---成功
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;
    /**
     * 返回其他数据
     */
    private Object Data;
}
