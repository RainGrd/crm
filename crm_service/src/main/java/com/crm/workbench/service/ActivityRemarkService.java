package com.crm.workbench.service;

import com.crm.workbench.entity.ActivityRemark;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/7 9:20
 * FileName: ActivityRemarkService
 * Description: 市场活动备注业务层
 */
public interface ActivityRemarkService {
    /**
     * 根据市场活动id查询市场活动备注
     */
    List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId);

    /**
     * 插入市场活动备注
     * @return int
     */
    int saveActivityRemark(ActivityRemark activityRemark);
    /**
     * 根据id删除市场活动备注
     */
    int deleteActivityRemarkById(String id);
    /**
     * 根据id修改市场活动备注
     */
    int updateActivityRemarkByActivityRemark(ActivityRemark activityRemark);
}
