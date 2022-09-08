package com.crm.wrokbench.service;

import com.crm.common.constants.Constants;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.workbench.entity.ActivityRemark;
import com.crm.workbench.service.ActivityRemarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 10:01
 * FileName: ActivityRemarkServiceTest
 * Description: 市场活动备注测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"})
public class ActivityRemarkServiceTest {
    @Autowired
    private ActivityRemarkService activityRemarkService;

    @Test
    public void insertCreateActivityRemark() {
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setNoteContent("备注测试01");
        activityRemark.setActivityId("e5bb65fb162b455e8a46bd022621b91e");
        //封装参数
        activityRemark.setId(UUIDUtils.getUUID());
        activityRemark.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        activityRemark.setCreateBy("40f6cdea0bd34aceb77492a1656d9fb3");
        activityRemark.setEditFlag(String.valueOf(0));
        activityRemarkService.saveActivityRemark(activityRemark);
    }
}
