package com.crm.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ResourceBundle;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 15:27
 * FileName: TransctionTest
 * Description: 交易控制测试层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"})
public class TransactionControllerTest {

    @Test
    public void getPossibilityByStage() {
        ResourceBundle possibility = ResourceBundle.getBundle("possibility");
        String possibilityString = possibility.getString("资质审查");
        System.out.println(possibilityString);
    }
}
