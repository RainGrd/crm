package com.crm.test;

import com.crm.common.utils.ImportExcelUtil;
import com.crm.workbench.entity.Activity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.apache.http.entity.ContentType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/6 10:50
 * FileName: ParseExcelTest
 * Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"})
public class ParseExcelTest {
    @Test
    public void test1() throws Exception {
        File file = new File("D:\\lenovo\\Desktop\\activityList.xls");
        MultipartFile mulFile = new MockMultipartFile(
                file.getName(), //文件名
                file.getName(), //originalName 相当于上传文件在客户机上的文件名
                ContentType.APPLICATION_OCTET_STREAM.toString(), //文件类型
                new FileInputStream(file) //文件流
        );
        List<String[]> list = ImportExcelUtil.readExcel(mulFile);
        List<Activity> activities = new ArrayList<>();
        Activity activity = new Activity();
        for (String[] strings : list) {
            for (int i = 0; i < strings.length; i++) {
                if (i == 0 && !strings[i].equals("")) {
                    activity.setId(strings[i]);
                } else if (i == 1 && !strings[i].equals("")) {
                    activity.setOwner(strings[i]);
                } else if (i == 2 && !strings[i].equals("")) {
                    activity.setName(strings[i]);
                } else if (i == 3 && !strings[i].equals("")) {
                    activity.setStartDate(strings[i]);
                } else if (i == 4 && !strings[i].equals("")) {
                    activity.setEndDate(strings[i]);
                } else if (i == 5 && !strings[i].equals("")) {
                    activity.setCost(strings[i]);
                } else if (i == 6 && !strings[i].equals("")) {
                    activity.setDescription(strings[i]);
                } else if (i == 7 && !strings[i].equals("")) {
                    activity.setCreateTime(strings[i]);
                } else if (i == 8 && !strings[i].equals("")) {
                    activity.setCreateBy(strings[i]);
                } else if (i == 9 && !strings[i].equals("")) {
                    activity.setEditTime(strings[i]);
                } else if (i == 10 && !strings[i].equals("")) {
                    activity.setEditBy(strings[i]);
                } else if (i == 11 && !strings[i].equals("")) {
                    activity.setActivityStatus(Integer.parseInt(strings[i]));
                }
            }
            activities.add(activity);
        }
        System.out.println(activities);
    }
}
