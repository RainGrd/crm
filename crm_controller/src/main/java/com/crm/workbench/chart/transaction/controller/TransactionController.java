package com.crm.workbench.chart.transaction.controller;

import com.crm.common.Vo.FunnelVO;
import com.crm.workbench.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/26 13:39
 * FileName: TransactionController
 * Description: 交易统计图表控制层
 */
@Controller("chartTransactionController")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    /**
     * 跳转至交易统计图表页面
     */
    @RequestMapping("workbench/chart/transaction/toTransactionIndex.do")
    public String toTransactionIndex() {
        System.out.println(1);
        return "workbench/chart/transaction/index";
    }

    /**
     * 查询所有交易阶段统计图表数据
     */
    @RequestMapping("workbench/chart/transaction/queryCountOfTransactionGroupByStage.do")
    @ResponseBody
    public String queryCountOfTransactionGroupByStage() throws JsonProcessingException {
        List<FunnelVO> funnelVOS = transactionService.queryCountOfTransactionGroupByStage();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(funnelVOS);
    }


}
