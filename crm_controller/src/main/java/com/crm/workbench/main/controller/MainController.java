package com.crm.workbench.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/26 14:23
 * FileName: MainController
 * Description: mainController
 */
@Controller
public class MainController {
    /**
     * 跳转至main目录下的index.html
     * @return
     */
    @RequestMapping("/workbench/main/index.do")
    public String index(){
        return "workbench/main/index";
    }

}
