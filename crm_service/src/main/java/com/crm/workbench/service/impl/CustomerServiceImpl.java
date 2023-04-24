package com.crm.workbench.service.impl;

import com.crm.common.utils.UUIDUtils;
import com.crm.workbench.entity.Customer;
import com.crm.workbench.mapper.CustomerMapper;
import com.crm.workbench.service.CustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    @Override
    public PageInfo<Customer> queryCustomerByConditionForPage(Map<String, String> map) {
        int beginNo = Integer.parseInt(map.get("beginNo"));
        int pageSize = Integer.parseInt(map.get("pageSize"));
        PageHelper.startPage(beginNo, pageSize);
        PageInfo<Customer> pageInfo = new PageInfo<>();
        pageInfo.setList(customerMapper.selectCustomerListByCondition(map));
        pageInfo.setTotal(customerMapper.selectCountByCondition(map));
        return pageInfo;
    }

    @Override
    public int saveCustomer(Customer customer) {
        return customerMapper.insertCustomer(customer);
    }
}
