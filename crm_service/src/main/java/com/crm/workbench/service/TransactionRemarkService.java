package com.crm.workbench.service;

import com.crm.workbench.entity.TransactionRemark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 7:42
 * FileName: TransactionRemarkService
 * Description: 交易备注业务层
 */
public interface TransactionRemarkService {
    /**
     * 根据交易Id查询交易备注
     */
    List<TransactionRemark> queryTransactionRemarkByTransactionId(@Param("transactionId") String transactionId);
}
