package com.crm.common.Vo;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 13:56
 * FileName: FunnelVO
 * Description: 传输数据类
 */
public class FunnelVO {
    /**
     *
     */
    private String name;
    /**
     *
     */
    private int value;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "FunnelVO{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
