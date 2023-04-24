package com.crm.settings.service;

import com.crm.settings.entity.DicValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 19:41
 * FileName: DicValueServiceTest
 * Description: 字典值业务层测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"})
public class DicValueServiceTest {


    @Autowired
    private DicValueService dicValueService;

    @Test
    public void queryDicValueByTypeCodeTest() {
        List<DicValue> appellation = dicValueService.queryDicValueByTypeCode("appellation");
        for (DicValue dicValue : appellation) {
            System.out.println(dicValue);
        }
    }

    @Test
    public void queryDicValueForPageTest() {
        Map<String, String> map = new HashMap<>();
        System.out.println(dicValueService.queryDicValueForPage("1", "5"));
    }
}
