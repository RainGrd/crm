package com.crm.settings.web.controller;

import com.crm.common.Vo.PageBean;
import com.crm.common.constants.Constants;
import com.crm.settings.service.UserService;
import com.crm.settings.entity.User;
import com.crm.common.utils.DateTimeUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/24 23:00
 * FileName: UserController
 * Description: 用户控制层
 */
@Controller
public class UserController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @Resource
    private UserService userService;

    /**
     * 跳转到登录页面
     *
     * @return
     */
    @RequestMapping("/settings/qx/user/toLogin.do")
    public String toLogin() {
        System.out.println("toLogin");
        return "settings/qx/user/login";
    }

    /**
     * 用户登录方法
     */
    @RequestMapping("/settings/qx/user/login.do")
    @ResponseBody
    public String login(String loginAct, String loginPwd, boolean idRemPwd, HttpSession session) throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);
        map.put("idRemPwd", idRemPwd);
        System.out.println(map);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = userService.queryUserLoginActAndPwd(map);
        /*根据查询结果，生成响应信息*/
        PageBean pageBean = new PageBean();
        if (user == null) {
            /*查询为空,登录失败*/
            pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
            pageBean.setMessage("用户名或密码错误！");
        } else {
            /*判断账号是否过期*/
            if (DateTimeUtil.ConvertDateStringYMD(new Date()).compareTo(user.getCreateTime()) > 0) {
                /*账号过期，登录失败*/
                pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
                pageBean.setMessage("账号过期！");
            } else
                /*判断账号是否被锁定*/
                if ("0".equals(user.getLockState())) {
                    /*账号锁定，登录失败*/
                    pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
                    pageBean.setMessage("账号锁定！");
                } else
                    /*判断IP是否受限*/
                    if (!user.getAllowIps().contains(request.getRemoteAddr())) {
                        /*ip受限，登录失败*/
                        pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
                        pageBean.setMessage("ip受限！");
                    } else {
                        /*登录成功！*/
                        pageBean.setCode(Constants.Page_BEAN_CODE_SUCCESS);
                        /*把User对象保存到session对象中*/
                        session.setAttribute(Constants.SESSION_USER, user);
                        /*判断用户是否需要记住密码*/
                        Cookie c1 = new Cookie("loginAct", loginAct);
                        Cookie c2 = new Cookie("loginPwd", loginPwd);
                        if (idRemPwd) {
                            /*指定cookie的存活时间*/
                            c1.setMaxAge(10 * 24 * 60 * 60);
                            c2.setMaxAge(10 * 24 * 60 * 60);
                            /*响应数据*/
                            response.addCookie(c1);
                            response.addCookie(c2);
                        } else {
                            /*清除cookie*/
                            /*指定cookie的存活时间*/
                            c1.setMaxAge(0);
                            c2.setMaxAge(0);
                            /*响应数据*/
                            response.addCookie(c1);
                            response.addCookie(c2);
                        }
                    }
        }
        return objectMapper.writeValueAsString(pageBean);
    }
    /**
     * 用于安全退出
     */
    @RequestMapping("/settings/qx/user/loginOut.do")
    public String loginOut(HttpSession session){
        /*清空cookie*/
        Cookie c1 = new Cookie("loginAct", "0");
        Cookie c2 = new Cookie("loginPwd", "0");
        c1.setMaxAge(0);
        c2.setMaxAge(0);
        /*响应数据*/
        response.addCookie(c1);
        response.addCookie(c2);
        /*销毁session*/
        session.invalidate();
        /*跳转至首页*/
        return "redirect:/";
    }
    /**
     * 获取session的用户对象
     */
    @RequestMapping("/settings/qx/user/getSessionUser.do")
    @ResponseBody
    public String getSessionUser(HttpSession session) throws JsonProcessingException {
        User user= (User) session.getAttribute(Constants.SESSION_USER);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(user);
    }

}

