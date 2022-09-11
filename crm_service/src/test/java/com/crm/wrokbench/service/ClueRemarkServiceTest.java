package com.crm.wrokbench.service;

import com.crm.workbench.service.ClueRemarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/11 14:33
 * FileName: ClueRemarkServiceTest
 * Description: 线索备注业务实现层测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"})
public class ClueRemarkServiceTest {
    @Autowired
    private ClueRemarkService clueRemarkService;


    @Test
    public void queryClueRemarkForDetailByClueIdTest() {
        System.out.println(clueRemarkService.queryClueRemarkForDetailByClueId("099491cfb8a74a89b9188bec3fbb4ad2"));
    }
}
