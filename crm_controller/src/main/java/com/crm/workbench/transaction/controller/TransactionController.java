package com.crm.workbench.transaction.controller;

import com.crm.settings.entity.DicValue;
import com.crm.settings.service.DicValueService;
import com.crm.settings.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/23 17:01
 * FileName: TransactionController
 * Description: 交易控制层
 */
@Controller
public class TransactionController {
    @Resource
    private UserService userService;
    @Resource
    private DicValueService dicValueService;

    /**
     * 跳转交易页面
     */
    @RequestMapping("/workbench/transaction/toIndex.do")
    public ModelAndView toIndex() {
        System.out.println("index");
        ModelAndView modelAndView = new ModelAndView();
        /*查询数据*/
        List<DicValue> stage = dicValueService.queryDicValueByTypeCode("stage");
        System.out.println(stage);
        List<DicValue> transactionType = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> source = dicValueService.queryDicValueByTypeCode("source");
        modelAndView.addObject("stageList", stage);
        modelAndView.addObject("transactionTypeList", transactionType);
        modelAndView.addObject("sourceList", source);
        modelAndView.setViewName("workbench/transaction/index");
        return modelAndView;
    }
}
