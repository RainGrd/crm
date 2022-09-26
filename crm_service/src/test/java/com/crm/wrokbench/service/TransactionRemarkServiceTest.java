package com.crm.wrokbench.service;

import com.crm.workbench.entity.TransactionRemark;
import com.crm.workbench.service.TransactionRemarkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 7:49
 * FileName: TransactionRemarkServiceTest
 * Description: 交易备注业务测试层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml", "classpath:applicationContext-service.xml"})
public class TransactionRemarkServiceTest {
    @Autowired
    private TransactionRemarkService transactionRemarkService;


    @Test
    public void queryTransactionRemarkByTransactionIdTest() {
        List<TransactionRemark> transactionRemarks = transactionRemarkService.queryTransactionRemarkByTransactionId("620498628c4744de92b83dab1a44d996");
        System.out.println(transactionRemarks);
        for (TransactionRemark transactionRemark : transactionRemarks) {
            System.out.println("transactionRemark = " + transactionRemark);
        }

    }
}
