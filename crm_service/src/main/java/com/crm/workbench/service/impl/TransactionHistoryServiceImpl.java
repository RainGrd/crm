package com.crm.workbench.service.impl;

import com.crm.workbench.entity.TransactionHistory;
import com.crm.workbench.mapper.TransactionHistoryMapper;
import com.crm.workbench.service.TransactionHistoryService;
import com.crm.workbench.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 8:16
 * FileName: TransactionHistoryServiceImpl
 * Description: 交易历史记录业务实现层
 */
@Service("transactionHistoryService")
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;


    @Override
    public List<TransactionHistory> queryTransactionHistoryListByTransactionHistoryId(String transactionId) {
        return transactionHistoryMapper.selectTransactionHistoryListByTransactionId(transactionId);
    }
}
