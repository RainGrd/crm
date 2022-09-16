package com.crm.workbench.service;

import com.crm.settings.entity.User;
import com.crm.workbench.entity.Activity;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

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
     *
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

    /**
     * 批量插入市场活动
     */
    int saveCreateActivityByList(List<String[]> activityList, User user);

    /**
     * 根基id查询单个市场活动
     */
    Activity queryActivityForDetail(String id);

    /**
     * 根据线索id查询市场活动备注
     *
     * @param clueId 线索id
     * @return java.util.List
     */
    List<Activity> queryActivityForDetailByClueId(String clueId);

    /**
     * 根据activityName和ClueId查询市场活动
     *
     * @param map
     * @return java.util.List
     */
    List<Activity> queryActivityByActivityNameAndClueId(Map<String, String> map);

    /**
     * 根据没关联的市场活动id数组来查询数据
     */
    List<Activity> queryActivityForDetailByIds(String[] activityIds);

    /**
     * 查询不想关联的市场活动
     */
    List<Activity> queryAssociatedActivityByActivityNameAndClueId(Map<String,String> map);
}
