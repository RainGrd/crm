package com.crm.common.constants;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/25 20:45
 * FileName: Constants
 * Description: 常量类
 */
public class Constants {
    /**
     * 成功的代码
     */
    public static final String PAGE_BEAN_CODE_SUCCESS = "1";
    /**
     * 失败的代码
     */
    public static final String PAGE_BEAN_CODE_FAIL = "0";
    /**
     * 定义年月日的常量
     */
    public static final String YMD = "yyyy-MM-dd";
    /**
     * 定义年月日时分秒的常量
     */
    public static final String YMD_HMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * 保存在会话对象的当前用户的key
     */
    public static final String SESSION_USER = "sessionUser";
    /**
     * office Excel文件名常量
     */
    public static final String XLS = "xls";
    /**
     * wps Excel文件名常量
     */
    public static final String XLSX = "xlsx";

    /*备注的修改标记*/
    public static final String ACTIVITY_REMARK_EDIT_FLAG_NO_EDITED = "0";  //无人修改
    public static final String ACTIVITY_REMARK_EDIT_FLAG_YES_EDITED = "1";  //已有修改
}
