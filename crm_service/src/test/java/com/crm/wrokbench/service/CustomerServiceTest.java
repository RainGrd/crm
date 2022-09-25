package com.crm.wrokbench.service;

import com.crm.workbench.entity.Customer;
import com.crm.workbench.service.CustomerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 20:48
 * FileName: CustomerServiceTest
 * Description: 客户业务测试层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml", "classpath:applicationContext-service.xml"})
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    @Test
    public void queryAllCustomerNameTest() {
        List<String> strings = customerService.queryAllCustomerNameByName("段");
        System.out.println("strings = " + strings);
    }

    @Test
    public void queryCustomerByCustomerNameTest() {
        Customer customer = customerService.queryCustomerByCustomerName("段荣贵");
        System.out.println("customer = " + customer);
    }
}

