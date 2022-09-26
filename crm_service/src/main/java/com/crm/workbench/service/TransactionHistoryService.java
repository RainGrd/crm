package com.crm.workbench.service;

import com.crm.workbench.entity.TransactionHistory;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 8:16
 * FileName: TransactionHistoryService
 * Description: 交易历史记录业务层
 */
public interface TransactionHistoryService {
    /**
     * 根据交易id查询交易多条历史记录
     */
    List<TransactionHistory> queryTransactionHistoryListByTransactionHistoryId(String transactionId);
}
