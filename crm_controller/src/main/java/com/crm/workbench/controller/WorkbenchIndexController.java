package com.crm.workbench.controller;

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
public class WorkbenchIndexController {
    /**
     * 用于跳转至工作页首页u89o0lk8iuhgbvbhj,
     * @return
     */
    @RequestMapping("/workbench/index.do")
    public String index() {
        System.out.println("index.do");
        /*直接跳转到业务主页面*/
        return "workbench/index";
    }

}
