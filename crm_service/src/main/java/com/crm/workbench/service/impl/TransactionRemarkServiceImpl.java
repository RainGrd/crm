package com.crm.workbench.service.impl;

import com.crm.workbench.entity.TransactionRemark;
import com.crm.workbench.mapper.TransactionRemarkMapper;
import com.crm.workbench.service.TransactionRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 7:42
 * FileName: TransactionRemarkServiceImpl
 * Description: 交易备注业务实现层
 */
@Service("transactionRemarkService")
public class TransactionRemarkServiceImpl implements TransactionRemarkService {

    @Autowired
    private TransactionRemarkMapper transactionRemarkMapper;

    @Override
    public List<TransactionRemark> queryTransactionRemarkByTransactionId(String transactionId) {
        return transactionRemarkMapper.selectTransactionRemarkByTransactionId(transactionId);
    }
}
