package com.crm.workbench.service.impl;

import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.User;
import com.crm.workbench.service.ActivityService;
import com.crm.workbench.entity.Activity;
import com.crm.workbench.mapper.ActivityMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        PageHelper.startPage(beginNo, pageSize);
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

    @Override
    public List<Activity> queryActivityList() {
        return activityMapper.selectActivityList();
    }

    @Override
    public List<String> queryActivityFieldByTableName(String tableName) {
        return activityMapper.findFieldByTableName(tableName);
    }

    @Override
    public List<Activity> queryActivityByIds(String[] ids) {
        return activityMapper.selectActivityByIds(ids);
    }

    @Override
    public int saveCreateActivityByList(List<String[]> list, User user) {
        List<Activity> activityList = new ArrayList<>();
        Activity activity = null;
        for (String[] str : list) {
            activity = new Activity();
            for (int i = 0; i < str.length; i++) {
                if (i == 0 && !str[i].equals("")) {
                    activity.setId(UUIDUtils.getUUID());
                } else if (i == 1 && !str[i].equals("")) {
                    activity.setOwner(user.getId());
                } else if (i == 2 && !str[i].equals("")) {
                    activity.setName(str[i]);
                } else if (i == 3 && !str[i].equals("")) {
                    activity.setStartDate(str[i]);
                } else if (i == 4 && !str[i].equals("")) {
                    activity.setEndDate(str[i]);
                } else if (i == 5 && !str[i].equals("")) {
                    activity.setCost(str[i]);
                } else if (i == 6 && !str[i].equals("")) {
                    activity.setDescription(str[i]);
                } else if (i == 7 && !str[i].equals("")) {
                    activity.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
                } else if (i == 8 && !str[i].equals("")) {
                    activity.setCreateBy(user.getId());
                } else if (i == 9 && !str[i].equals("")) {
                    activity.setEditTime(str[i]);
                } else if (i == 10 && !str[i].equals("")) {
                    activity.setEditBy(str[i]);
                } else if (i == 11 && !str[i].equals("")) {
                    activity.setActivityStatus(Integer.parseInt(str[i]));
                }
            }
            activityList.add(activity);
        }
        return activityMapper.insertActivityByList(activityList);
    }

    @Override
    public Activity queryActivityForDetail(String id) {
        return activityMapper.selectActivityForDetailById(id);
    }

    @Override
    public List<Activity> queryActivityForDetailByClueId(String clueId) {
        return activityMapper.selectActivityForDetailByClueId(clueId);
    }

    @Override
    public List<Activity> queryActivityByActivityNameAndClueId(Map<String, String> map) {
        return activityMapper.selectActivityByActivityNameAndClueId(map);
    }

    @Override
    public List<Activity> queryActivityForDetailByIds(String[] activityIds) {
        return activityMapper.selectActivityForDetailByIds(activityIds);
    }

    @Override
    public List<Activity> queryAssociatedActivityByActivityNameAndClueId(Map<String, String> map) {
        return activityMapper.selectAssociatedActivityByActivityNameAndClueId(map);
    }

    @Override
    public List<Activity> queryAssociatedActivityByActivityName(Map<String,String> map) {
        return activityMapper.selectAssociatedActivityByActivityName(map);
    }
}
