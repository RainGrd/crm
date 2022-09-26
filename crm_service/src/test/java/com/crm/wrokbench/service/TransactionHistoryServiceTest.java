package com.crm.wrokbench.service;

import com.crm.workbench.entity.TransactionHistory;
import com.crm.workbench.service.TransactionHistoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 8:19
 * FileName: TransactionHistoryServiceTest
 * Description: 交易历史记录测试层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml", "classpath:applicationContext-service.xml"})
public class TransactionHistoryServiceTest {
    @Autowired
    private TransactionHistoryService transactionHistoryService;


    @Test
    public void queryTransactionHistoryListByTransactionHistoryIdTest() {
        List<TransactionHistory> transactionHistories = transactionHistoryService.queryTransactionHistoryListByTransactionHistoryId("1e11d83442714c71847878e19d897454");
        for (TransactionHistory transactionHistory : transactionHistories) {
            System.out.println("transactionHistory = " + transactionHistory);
        }
    }
}
