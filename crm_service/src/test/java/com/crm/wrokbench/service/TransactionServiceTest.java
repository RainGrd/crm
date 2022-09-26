package com.crm.wrokbench.service;

import com.crm.workbench.entity.Transaction;
import com.crm.workbench.service.TransactionService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 8:13
 * FileName: TransactionServiceTest
 * Description: 交易业务测试层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml", "classpath:applicationContext-service.xml"})
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void queryTransactionListByConditionForPageTest() {
        Map<String, String> map = new HashMap<>();
        map.put("pageNum", "1");
        map.put("pageSize", "5");
        PageInfo<Transaction> transactionPageInfo = transactionService.queryTransactionListByConditionForPage(map);
        List<Transaction> list = transactionPageInfo.getList();
        for (Transaction transaction : list) {
            System.out.println(transaction);
        }
    }

    @Test
    public void queryTransactionByTransactionIdTest() {
        Transaction transaction = transactionService.queryTransactionByTransactionId("1e11d83442714c71847878e19d897454");
        System.out.println("transaction = " + transaction);
    }
}
