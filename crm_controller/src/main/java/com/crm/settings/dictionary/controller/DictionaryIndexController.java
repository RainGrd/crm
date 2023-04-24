package com.crm.settings.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Classname DictionaryIndexController
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/7 16:24
 * @Author RainGrd
 */
@Controller
public class DictionaryIndexController {
    /**
     * 跳转值数据字典首页
     *
     * @return
     */
    @RequestMapping("/settings/dictionary/index.do")
    public String index() {
        return "settings/dictionary/index";
    }


}
