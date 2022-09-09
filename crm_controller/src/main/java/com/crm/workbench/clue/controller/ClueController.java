package com.crm.workbench.clue.controller;

import com.crm.common.Vo.PageBean;
import com.crm.common.constants.ConstantsEnum;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.DicValue;
import com.crm.settings.entity.User;
import com.crm.settings.service.DicValueService;
import com.crm.settings.service.UserService;
import com.crm.workbench.entity.Clue;
import com.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 18:57
 * FileName: ClueController
 * Description: 线索控制层
 */
@Controller
public class ClueController {
    @Resource
    private UserService userService;
    @Resource
    private DicValueService dicValueService;
    @Autowired
    private ClueService clueService;

    /**
     * 跳转主页面
     */
    @RequestMapping("/workbench/clue/index.do")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.queryAllUsers();
        List<DicValue> appellationList = dicValueService.queryDicValueByTypeCode("appellation");
        List<DicValue> clueStateList = dicValueService.queryDicValueByTypeCode("clueState");
        List<DicValue> sourceList = dicValueService.queryDicValueByTypeCode("source");
        modelAndView.addObject("users", users);
        modelAndView.addObject("appellationList", appellationList);
        modelAndView.addObject("clueStateList", clueStateList);
        modelAndView.addObject("sourceList", sourceList);
        /*请求转发*/
        modelAndView.setViewName("workbench/clue/index");
        return modelAndView;
    }
    /**
     * 插入线索
     */
    @RequestMapping("/workbench/clue/saveCreateClue.do")
    @ResponseBody
    public Object saveCreateClue(@RequestBody Clue clue, HttpSession session){
        User user= (User) session.getAttribute(ConstantsEnum.SESSION_USER.getStr());
        PageBean pageBean = new PageBean();
        /*封装参数*/
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        clue.setCreateBy(user.getId());
        try {
            int saveClue = clueService.saveClue(clue);
            if (saveClue>0) {
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_SUCCESS.getStr());
            }else{
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_FAIL.getStr());
                pageBean.setMessage("系统忙，正在维护中...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_FAIL.getStr());
            pageBean.setMessage("系统忙，正在维护中...");
        }
        return pageBean;
    }
}
