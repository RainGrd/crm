package com.crm.workbench.service.impl;

import com.crm.workbench.entity.Transaction;
import com.crm.workbench.mapper.TransactionMapper;
import com.crm.workbench.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 7:37
 * FileName: TransactionServiceImpl
 * Description: 交易业务实现层
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;


    @Override
    public PageInfo<Transaction> queryTransactionListByConditionForPage(Map<String, String> map) {
        int pageNum = Integer.parseInt(map.get("pageNum"));
        int pageSize = Integer.parseInt(map.get("pageSize"));
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Transaction> pageInfo = new PageInfo<>();
        pageInfo.setList(transactionMapper.selectTransactionListByConditionForPage(map));
        pageInfo.setTotal(transactionMapper.selectCountByCondition(map));
        return pageInfo;
    }
}
