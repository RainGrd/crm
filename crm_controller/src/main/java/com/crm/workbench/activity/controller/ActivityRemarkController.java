package com.crm.workbench.activity.controller;

import com.crm.common.Vo.PageBean;
import com.crm.common.constants.ConstantsEnum;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.User;
import com.crm.workbench.entity.ActivityRemark;
import com.crm.workbench.service.ActivityRemarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/7 10:45
 * FileName: ActivityRemarkController
 * Description: 市场活动备注控制层
 */
@Controller
public class ActivityRemarkController {
    @Autowired
    private ActivityRemarkService activityRemarkService;

    /**
     * 插入市场活动
     */
    @RequestMapping("/workbench/activity/saveCreateActivityRemark.do")
    @ResponseBody
    public Object saveCreateActivityRemark(ActivityRemark activityRemark, HttpSession session) {
        PageBean pageBean = new PageBean();
        User user = (User) session.getAttribute(ConstantsEnum.SESSION_USER.getStr());
        //封装参数
        activityRemark.setId(UUIDUtils.getUUID());
        activityRemark.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        activityRemark.setCreateBy(user.getId());
        activityRemark.setEditFlag(String.valueOf(ConstantsEnum.ACTIVITY_REMARK_EDIT_FLAG_NO_EDITED.getStr()));
        activityRemark.setActivityRemarkStatus(Integer.valueOf(ConstantsEnum.ACTIVITY_REMARK_STATUS_YES_EDITED.getStr()));
        System.out.println(activityRemark);
        try {
            int remark = activityRemarkService.saveActivityRemark(activityRemark);
            if (remark > 0) {
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_SUCCESS.getStr());
                pageBean.setData(activityRemark);
            } else {
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_FAIL.getStr());
                pageBean.setMessage("系统忙，请稍后重试...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_FAIL.getStr());
            pageBean.setMessage("系统忙，请稍后重试...");
        }
        return pageBean;
    }

    /**
     * 根据id删除市场活动备注
     */
    @RequestMapping("/workbench/activity/deleteActivityRemarkById.do")
    @ResponseBody
    public Object deleteActivityRemarkById(String id) {
        PageBean pageBean = new PageBean();
        try {
            int remarkById = activityRemarkService.deleteActivityRemarkById(id);
            if (remarkById > 0) {
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_SUCCESS.getStr());
            } else {
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_FAIL.getStr());
                pageBean.setMessage("系统忙，正在修复中...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_FAIL.getStr());
            pageBean.setMessage("系统忙，正在修复中...");
        }
        return pageBean;
    }

    /**
     * 根据id修改市场活动备注
     */
    @RequestMapping("/workbench/activity/updateActivityRemarkByActivityRemark.do")
    @ResponseBody
    public Object updateActivityRemarkByActivityRemark(ActivityRemark activityRemark, HttpSession session) {
        PageBean pageBean = new PageBean();
        /*封装参数*/
        User user = (User) session.getAttribute(ConstantsEnum.SESSION_USER.getStr());
        activityRemark.setEditTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        activityRemark.setEditBy(user.getId());
        activityRemark.setEditFlag(ConstantsEnum.ACTIVITY_REMARK_EDIT_FLAG_NO_EDITED.getStr());
        System.out.println(activityRemark);
        try {
            int remark = activityRemarkService.updateActivityRemarkByActivityRemark(activityRemark);
            if (remark > 0) {
                pageBean.setCode(ConstantsEnum.Page_BEAN_CODE_SUCCESS.getStr());
                pageBean.setData(activityRemark);
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
}

