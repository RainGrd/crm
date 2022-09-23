package com.crm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/24 22:39
 * FileName: IndexControoler
 * Description: 首页控制器类
 */
@Controller
public class IndexController {
    /**
     * 接受请求，跳转首页
     */
    @RequestMapping("/")
    public String index(){
        System.out.println("index");
        return "redirect:index";
    }
}
