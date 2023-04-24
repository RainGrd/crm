package com.crm.settings.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname SettingsIndexController
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/7 15:43
 * @Author RainGrd
 */
@Controller
public class SettingsIndexController {

    /**
     * 跳转系统设置页面
     *
     * @return
     */
    @RequestMapping("settings/toIndex.do")
    public String toIndex() {
        return "settings/index";
    }
}
