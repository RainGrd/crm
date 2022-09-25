package com.crm.workbench.service;

import com.crm.workbench.entity.Customer;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 20:42
 * FileName: CustomerService
 * Description: 客户业务层
 */
public interface CustomerService {

    /**
     * 查询所有客户名称
     */
    List<String> queryAllCustomerNameByName(String name);
    /**
     * 根据客户名称查询客户对象
     */
    Customer queryCustomerByCustomerName(String customerName);
}
