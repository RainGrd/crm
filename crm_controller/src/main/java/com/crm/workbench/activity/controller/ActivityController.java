package com.crm.workbench.activity.controller;

import com.crm.common.Vo.PageBean;
import com.crm.common.constants.Constants;
import com.crm.common.utils.LocalDateTimeUtils;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.User;
import com.crm.settings.service.UserService;
import com.crm.workbench.entity.Activity;
import com.crm.workbench.service.ActivityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/26 15:14
 * FileName: ActivtiyController
 * Description: 市场活动控制器
 */
@Controller
public class ActivityController {
    @Resource
    private UserService userService;
    @Autowired
    private ActivityService activityService;

    private final ObjectMapper objectMapper=new ObjectMapper();

    /**
     * 查询所有用户
     */
    @RequestMapping("/workbench/activity/index.do")
    public ModelAndView index() throws JsonProcessingException {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList", userService.queryAllUsers());
        modelAndView.setViewName("workbench/activity/index");
        return modelAndView;
    }

    /**
     * 保存创建市场活动
     */
    @RequestMapping("/workbench/activity/saveCreateActivity.do")
    @ResponseBody
    public String saveCreateActivity(Activity activity, HttpSession session) throws JsonProcessingException {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        /*封装参数*/
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(LocalDateTimeUtils.convertDateCustomStringFormat(new Date()));
        /*将当前登录用户的ID值封装，这样可以防止重复*/
        activity.setCreateBy(user.getId());
        PageBean pageBean = new PageBean();
        try {
            /*判断业务是否报异常*/
            int createActivity = activityService.saveCreateActivity(activity);
            if (createActivity <= 0) {
                pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
                pageBean.setMessage("系统忙，请稍后重试......");
            } else {
                pageBean.setCode(Constants.Page_BEAN_CODE_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
            pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
            pageBean.setMessage("系统忙，请稍后重试......");
        }
        return new ObjectMapper().writeValueAsString(pageBean);
    }

    /**
     * 根据条件查询分页活动
     */
    @RequestMapping(value = "/workbench/activity/queryActivityByConditionForPage.do", method = RequestMethod.POST)
    @ResponseBody
    public String queryActivityByConditionForPage(@RequestBody Activity activity, @RequestParam("beginNo") int beginNo, @RequestParam("pageSize") int pageSize) throws JsonProcessingException {
        PageInfo<Activity> pageInfo = activityService.queryActivityAndCountByConditionForPage(activity, beginNo, pageSize);
        /*根据查询结果,生成响应信息*/

        return objectMapper.writeValueAsString(pageInfo);
    }

    /**
     * 根据id删除市场活动
     */
    @RequestMapping("/workbench/activity/deleteActivityByIds.do")
    @ResponseBody
    public Object deleteActivityByIds(@RequestParam(value = "ids[]") String[] ids) {
        System.out.println(ids);
        /*调用市场活动*/
        int byIds = 0;
        PageBean pageBean = new PageBean();
        try {
            byIds = activityService.deleteActivityByIds(ids);
            System.out.println(byIds);
            if (byIds > 0) {
                pageBean.setCode(Constants.Page_BEAN_CODE_SUCCESS);
            } else {
                pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
                pageBean.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
            pageBean.setMessage("系统忙，请稍后重试...");
        }
        return pageBean;
    }

    /**
     * 根据Id查询活动
     */
    @RequestMapping("/workbench/activity/queryActivityById.do")
    @ResponseBody
    public String queryActivityById(@RequestParam String id) throws JsonProcessingException {
        System.out.println(activityService.queryActivityById(id));
        return objectMapper.writeValueAsString(activityService.queryActivityById(id));
    }
    /**
     * 根据id修改市场活动
     */
    @RequestMapping("workbench/activity/updateActivityById.do")
    @ResponseBody
    public Object updateActivityById(@RequestBody Activity activity){
        PageBean pageBean=new PageBean();
        try {
            int updateActivityById = activityService.updateActivityById(activity);
            if(updateActivityById>0){
                pageBean.setCode(Constants.Page_BEAN_CODE_SUCCESS);
            }else {
                pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
                pageBean.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
            pageBean.setMessage("系统忙，请稍后重试...");
        }
        return pageBean;
    }

}
