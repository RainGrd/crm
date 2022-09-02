package com.crm.wrokbench.service;

import com.crm.workbench.entity.Activity;
import com.crm.workbench.service.ActivityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/26 20:43
 * FileName: ActivityServiceTest
 * Description: 市场活动业务层测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml","classpath:applicationContext-dao.xml"})
public class ActivityServiceTest {
    @Autowired
    private ActivityService activityService;
    @Test
    public void insertActivity(){
        Activity activity = new Activity();
        activity.setId(UUID.randomUUID().toString().replace("-",""));
        activity.setOwner("张三");
        activity.setName("测试01");
        activity.setStartDate("2020-10-20");
        activity.setEndDate("2020-10-21");
        activity.setCost("100");
        activity.setDescription("描述测试01");
        activity.setCreateTime("2022-08-26");
        activity.setCreateBy("40f6cdea0bd34aceb77492a1656d9fb3");
        activityService.saveCreateActivity(activity);
    }
}
