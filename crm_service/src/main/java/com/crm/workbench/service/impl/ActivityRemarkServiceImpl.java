package com.crm.workbench.service.impl;

import com.crm.workbench.entity.ActivityRemark;
import com.crm.workbench.mapper.ActivityRemarkMapper;
import com.crm.workbench.service.ActivityRemarkService;
import com.crm.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/7 9:22
 * FileName: ActivityRemarkServiceImpl
 * Description: 市场活动备注业务实现类
 */
@Service("activityRemarkService")
public class ActivityRemarkServiceImpl implements ActivityRemarkService {

    @Autowired
    private ActivityRemarkMapper activityRemarkMapper;

    @Override
    public List<ActivityRemark> queryActivityRemarkForDetailByActivityId(String activityId) {
        return activityRemarkMapper.selectActivityRemarkForDetailByActivityId(activityId);
    }
}
