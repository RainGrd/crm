package com.crm.workbench.service;

import com.crm.workbench.entity.Transaction;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 7:36
 * FileName: TransactionService
 * Description: 交易业务层
 */
public interface TransactionService {
    /**
     * 分页查询方法
     */
    PageInfo<Transaction> queryTransactionListByConditionForPage(Map<String,String> map);
    /**
     * 保存的创建交易
     */
    void saveTransaction(Map<String,Object> map);
}
