package com.crm.common.constants;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 11:16
 * FileName: Enumeration
 * Description: 给page对象定义的枚举类
 */
public enum ConstantsEnum {
    /**
     * 传送成功的代码
     */
    PAGE_BEAN_CODE_SUCCESS("1"),
    /**
     * 传送失败的代码
     */
    PAGE_BEAN_CODE_FAIL("0"),
    /**
     * 定义年月日的常量
     */
    YMD("yyyy-MM-dd"),
    /**
     * 定义年月日时分秒的常量
     */
    YMD_HMS("yyyy-MM-dd HH:mm:ss"),
    /**
     * 保存在会话对象的当前用户的key
     */
    SESSION_USER("sessionUser"),
    /**
     * office Excel文件名常量
     */
    XLS("xls"),
    /**
     * wps Excel文件名常量
     */
    XLSX("xlsx"),
    /**
     * 已修改市场活动备注的状态
     */
    ACTIVITY_REMARK_EDIT_FLAG_NO_EDITED("0"),
    /**
     * 未修改市场活动备注的状态
     */
    ACTIVITY_REMARK_EDIT_FLAG_YES_EDITED("1"),
    /**
     * 定义市场备注状态
     */
    ACTIVITY_REMARK_STATUS_YES_EDITED("0"),    //未除
    ACTIVITY_REMARK_STATUS_NO_EDITED("1");     //删除
    /**
     * 值
     */
    private final String str;

    ConstantsEnum(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }
}
