package com.crm.workbench.service.impl;

import com.crm.workbench.entity.Customer;
import com.crm.workbench.mapper.CustomerMapper;
import com.crm.workbench.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 20:43
 * FileName: CustomerServiceImpl
 * Description: 客户业务实现层
 */
@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    @Override
    public List<String> queryAllCustomerNameByName(String name) {
        return customerMapper.selectAllCustomerNameByName(name);
    }

    @Override
    public Customer queryCustomerByCustomerName(String customerName) {
        return customerMapper.selectCustomerByCustomerName(customerName);
    }
}
