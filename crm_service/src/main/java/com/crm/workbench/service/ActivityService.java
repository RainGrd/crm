package com.crm.workbench.service;

import com.crm.workbench.entity.Activity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/26 16:40
 * FileName: ActivityService
 * Description: 市场活动业务层
 */
public interface ActivityService {

    /**
     * 保存市场活动
     */
    int saveCreateActivity(Activity activity);

    /**
     * 根据条件查询数据
     * @return java.util.List
     */
    PageInfo<Activity> queryActivityAndCountByConditionForPage(Activity activity, int beginNo, int pageSize);
    /**
     * 根据id值删除指定市场活动
     */
    int deleteActivityByIds(String ids[]);
    /**
     * 根据id查找市场活动
     */
    Activity queryActivityById(String id);
    /**
     * 根据id修改市场活动
     */
    int updateActivityById(Activity activity);
    /**
     * 查询所有
     */
    List<Activity> queryActivityList();
    /**
     * 根据表名查询列名
     */
    List<String> queryActivityFieldByTableName(String tableName);
    /**
     * 根据id数组查询市场活动
     */
    List<Activity> queryActivityByIds(String[] ids);
}
