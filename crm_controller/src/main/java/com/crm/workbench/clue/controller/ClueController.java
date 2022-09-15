package com.crm.workbench.clue.controller;

import com.crm.common.Vo.PageBean;
import com.crm.common.constants.ConstantsEnum;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.DicValue;
import com.crm.settings.entity.User;
import com.crm.settings.service.DicValueService;
import com.crm.settings.service.UserService;
import com.crm.workbench.entity.Activity;
import com.crm.workbench.entity.Clue;
import com.crm.workbench.entity.ClueRemark;
import com.crm.workbench.service.ActivityService;
import com.crm.workbench.service.ClueRemarkService;
import com.crm.workbench.service.ClueService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
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
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 18:57
 * FileName: ClueController
 * Description: 线索控制层
 */
@Controller("clueController")
public class ClueController {
    @Resource
    private UserService userService;
    @Resource
    private DicValueService dicValueService;
    @Autowired
    private ClueService clueService;
    @Autowired
    private ClueRemarkService clueRemarkService;
    @Autowired
    private ActivityService activityService;

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
    public Object saveCreateClue(Clue clue, HttpSession session) {
        User user = (User) session.getAttribute(ConstantsEnum.SESSION_USER.getStr());
        PageBean pageBean = new PageBean();
        /*封装参数*/
        clue.setId(UUIDUtils.getUUID());
        clue.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        clue.setCreateBy(user.getId());
        System.out.println(clue);
        try {
            int saveClue = clueService.saveClue(clue);
            if (saveClue > 0) {
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_SUCCESS.getStr());
            } else {
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

    /**
     * 根据条件分页查询
     */
    @RequestMapping("/workbench/clue/queryClueByConditionForPage.do")
    @ResponseBody
    public String queryClueByConditionForPage(@RequestBody Map<String, String> map) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        PageInfo<Clue> cluePageInfo = clueService.queryClueByConditionForPage(map);
        return objectMapper.writeValueAsString(cluePageInfo);
    }

    @RequestMapping("/workbench/clue/detailClue.do")
    public ModelAndView detailClue(String id) {
        ModelAndView modelAndView = new ModelAndView();
        /*调用service查询数据*/
        Clue clue = clueService.queryClueForDetailById(id);
        System.out.println(clue);
        List<ClueRemark> clueRemarkList = clueRemarkService.queryClueRemarkForDetailByClueId(id);
        List<Activity> activityList = activityService.queryActivityForDetailByClueId(id);
        /*存放数据*/
        modelAndView.addObject("clue", clue);
        modelAndView.addObject("clueRemarkList", clueRemarkList);
        modelAndView.addObject("activityList", activityList);
        /*跳转视图*/
        modelAndView.setViewName("workbench/clue/detail");
        return modelAndView;
    }

    /**
     * 根据市场活动名称和线索id查询市场活动
     */
    @RequestMapping("/workbench/clue/queryActivityByActivityNameAndClueId.do")
    @ResponseBody
    public String queryActivityByActivityNameAndClueId(@RequestBody Map<String, String> map) throws JsonProcessingException {
        System.out.println(map);
        List<Activity> activities = activityService.queryActivityByActivityNameAndClueId(map);
        ObjectMapper objectMapper = new ObjectMapper();
        /*返回数据*/
        return objectMapper.writeValueAsString(activities);
    }
}