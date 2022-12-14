package com.crm.wrokbench.service;

import com.crm.settings.entity.User;
import com.crm.workbench.entity.Activity;
import com.crm.workbench.service.ActivityService;
import org.apache.http.entity.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/26 20:43
 * FileName: ActivityServiceTest
 * Description: 市场活动业务层测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"})
public class ActivityServiceTest {
    @Autowired
    private ActivityService activityService;

    @Test
    public void insertActivity() {
        Activity activity = new Activity();
        activity.setId(UUID.randomUUID().toString().replace("-", ""));
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

    @Test
    public void queryActivityList() {
        System.out.println(activityService.queryActivityList());
    }

    @Test
    public void queryActivityFieldByTableName() {
        System.out.println(activityService.queryActivityFieldByTableName("tbl_activity"));
    }

    @Test
    public void queryActivityListByIds() {
        String[] ids = {"39981e5f2dff482b91f3bf5818e3ceab", "4a83f3b17d954bbd9be5bc4d85937c48", "802b0c47de3c4bf1b466d38dd82fe0b4"};
        System.out.println(activityService.queryActivityByIds(ids));
    }

    @Test
    public void saveCreateActivityByList() throws Exception {
        File file = new File("D:\\lenovo\\Desktop\\activityList.xls");
        MultipartFile mulFile = new MockMultipartFile(
                file.getName(), //文件名
                file.getName(), //originalName 相当于上传文件在客户机上的文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(), //文件类型
                new FileInputStream(file) //文件流
        );
        List<String[]> list = ImportExcelUtil.readExcel(mulFile);
        User user = new User();
        user.setId("40f6cdea0bd34aceb77492a1656d9fb3");
        activityService.saveCreateActivityByList(list, user);
    }

    @Test
    public void queryActivityForDetailByIdsTest() {
        String[] ids = new String[10];
        ids[0] = "48391170784c46c9b0b2f86943586709";
        ids[1] = "4a83f3b17d954bbd9be5bc4d85937c48";
        System.out.println(activityService.queryActivityForDetailByIds(ids));
    }

    @Test
    public void queryAssociatedActivityByActivityIdTest() {
        Map<String, String> map = new HashMap<>();
        map.put("activityName","测试");
        List<Activity> activities = activityService.queryAssociatedActivityByActivityName(map);
        for (Activity activity : activities) {
            System.out.println("activity = " + activity);
        }
    }
}
