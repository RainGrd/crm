package com.crm.workbench.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/25 17:42
 * FileName: WorkbenchIndexController
 * Description: 跳转到首页的控制器
 */
@Controller
@Slf4j
public class WorkbenchIndexController {
    /**
     * 用于跳转至工作页首页
     * @return
     */
    @RequestMapping("/workbench/index.do")
    public String index() {
        log.info("index.do");
        /*直接跳转到业务主页面*/
        return "workbench/index";
    }

}
