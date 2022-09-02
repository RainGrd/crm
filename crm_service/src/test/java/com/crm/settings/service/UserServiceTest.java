package com.crm.settings.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/25 9:49
 * FileName: UserServiceTest
 * Description: 用户业务层测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml","classpath:applicationContext-dao.xml"})
public class UserServiceTest {
    @Autowired
    private UserService userService;
    /**
     * 查询全部
     */
    @Test
    public void testSelectAll(){
        System.out.println(userService.queryAllUsers());
    }
}
