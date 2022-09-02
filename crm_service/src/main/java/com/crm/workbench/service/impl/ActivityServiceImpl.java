package com.crm.workbench.service.impl;

import com.crm.workbench.service.ActivityService;
import com.crm.workbench.entity.Activity;
import com.crm.workbench.mapper.ActivityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/26 16:41
 * FileName: AtivityServiceImpl
 * Description: 市场活动业务层实现类
 */
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Override
    public int saveCreateActivity(Activity activity) {
        return activityMapper.insertActivity(activity);
    }

    public PageInfo<Activity> queryActivityAndCountByConditionForPage(Activity activity, int beginNo, int pageSize) {
        PageHelper.startPage(beginNo,pageSize);
        PageInfo<Activity> pageInfo = new PageInfo<>();
        pageInfo.setList(activityMapper.selectActivityByConditionForPage(activity));
        pageInfo.setTotal(activityMapper.selectCountOfActivityByCondition(activity));
        return pageInfo;
    }

    @Override
    public int deleteActivityByIds(String[] ids) {
        return activityMapper.deleteActivityByIds(ids);
    }

    @Override
    public Activity queryActivityById(String id) {
        return activityMapper.selectActivityById(id);
    }

    @Override
    public int updateActivityById(Activity activity) {
        return activityMapper.updateActivityById(activity);
    }

}
