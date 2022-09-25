package com.crm.workbench.transaction.controller;

import com.crm.common.Vo.PageBean;
import com.crm.common.constants.ConstantsEnum;
import com.crm.settings.entity.DicValue;
import com.crm.settings.entity.User;
import com.crm.settings.service.DicValueService;
import com.crm.settings.service.UserService;
import com.crm.workbench.entity.Activity;
import com.crm.workbench.entity.Contacts;
import com.crm.workbench.entity.Transaction;
import com.crm.workbench.service.ActivityService;
import com.crm.workbench.service.ContactsService;
import com.crm.workbench.service.CustomerService;
import com.crm.workbench.service.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/23 17:01
 * FileName: TransactionController
 * Description: 交易控制层
 */
@Controller
public class TransactionController {
    @Resource
    private UserService userService;
    @Resource
    private DicValueService dicValueService;
    @Autowired
    private TransactionService transactionService;
    @Resource
    private CustomerService customerService;
    @Autowired
    private ContactsService contactsService;
    @Autowired
    private ActivityService activityService;

    /**
     * 跳转交易页面
     */
    @RequestMapping("/workbench/transaction/toIndex.do")
    public ModelAndView toIndex() {
        System.out.println("index");
        ModelAndView modelAndView = new ModelAndView();
        /*查询数据*/
        List<DicValue> stage = dicValueService.queryDicValueByTypeCode("stage");
        System.out.println(stage);
        List<DicValue> transactionType = dicValueService.queryDicValueByTypeCode("transactionType");
        List<DicValue> source = dicValueService.queryDicValueByTypeCode("source");
        modelAndView.addObject("stageList", stage);
        modelAndView.addObject("transactionTypeList", transactionType);
        modelAndView.addObject("sourceList", source);
        modelAndView.setViewName("workbench/transaction/index");
        return modelAndView;
    }

    /**
     * 分页查询
     */
    @RequestMapping("/workbench/transaction/queryTransactionListByConditionForPage.do")
    @ResponseBody
    public String queryTransactionListByConditionForPage(@RequestBody Map<String, String> map) throws JsonProcessingException {
        System.out.println(map);
        ObjectMapper objectMapper = new ObjectMapper();
        PageInfo<Transaction> transactionPageInfo = transactionService.queryTransactionListByConditionForPage(map);
        System.out.println(transactionPageInfo);
        return objectMapper.writeValueAsString(transactionPageInfo);
    }

    /**
     * 跳转创建交易页面
     */
    @RequestMapping("/workbench/transaction/toSaveTransaction.do")
    public ModelAndView toSaveTransaction() {
        ModelAndView modelAndView = new ModelAndView();
        /*查询所有者*/
        List<User> users = userService.queryAllUsers();
        /*查询阶段*/
        List<DicValue> stage = dicValueService.queryDicValueByTypeCode("stage");
        /*查询类型*/
        List<DicValue> transactionType = dicValueService.queryDicValueByTypeCode("transactionType");
        /*查询来源*/
        List<DicValue> source = dicValueService.queryDicValueByTypeCode("source");
        modelAndView.addObject("users", users);
        modelAndView.addObject("stageList", stage);
        modelAndView.addObject("transactionTypeList", transactionType);
        modelAndView.addObject("sourceList", source);
        /*设置跳转视图*/
        modelAndView.setViewName("workbench/transaction/save");
        return modelAndView;
    }

    /**
     * 获取配置文件中阶段可能性
     */
    @RequestMapping("/workbench/transaction/getPossibilityByStage.do")
    @ResponseBody
    public String getPossibilityByStage(@RequestParam("stage") String stage) {
        System.out.println(stage);
        /*解压配置文件*/
        ResourceBundle possibility = ResourceBundle.getBundle("possibility");
        /*根据传过来的参数进行取值*/
        String possibilityString = possibility.getString(stage);
        System.out.println(possibilityString);
        /*返回响应信息*/
        return possibilityString;
    }

    /**
     * 客户名称自动补全
     */
    @RequestMapping("/workbench/transaction/queryAllCustomerName.do")
    @ResponseBody
    public String queryAllCustomerNameByName(@RequestParam("name") String name) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> list = customerService.queryAllCustomerNameByName(name);
        return objectMapper.writeValueAsString(list);
    }

    /**
     * 保存创建的客户对象
     */
    @RequestMapping("/workbench/transaction/saveCreateCustomer.do")
    @ResponseBody
    public Object saveCreateCustomer(@RequestBody Map<String, Object> map, HttpSession session) {
        PageBean pageBean = new PageBean();
        try {
            /*封装数据*/
            map.put(ConstantsEnum.SESSION_USER.getStr(), session.getAttribute(ConstantsEnum.SESSION_USER.getStr()));
            transactionService.saveTransaction(map);
            pageBean.setCode(ConstantsEnum.PAGE_BEAN_CODE_SUCCESS.getStr());
        } catch (Exception e) {
            pageBean.setCode(ConstantsEnum.PAGE_BEAN_CODE_FAIL.getStr());
            pageBean.setMessage("系统忙，正在维护中！");
            e.printStackTrace();
        }
        return pageBean;
    }

    /**
     * 根据联系人名称查询联系人
     */
    @RequestMapping(value = "/workbench/transaction/queryContactsListByContactsName.do")
    @ResponseBody
    public String queryContactsListByContactsName(String contactsName) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        //contactsName = new String(contactsName.getBytes(ConstantsEnum.CHAT_ENCODING_ISO88591.getStr()), ConstantsEnum.CHAT_ENCODING_UTF8.getStr());
        System.out.println(contactsName);
        List<Contacts> contacts = contactsService.queryContactsListByContactsName(contactsName);
        System.out.println("contacts = " + contacts);
        return objectMapper.writeValueAsString(contacts);
    }

    /**
     * 查询未被交易关联的市场活动
     */
    @RequestMapping("/workbench/transaction/queryAssociatedActivityByActivityName.do")
    @ResponseBody
    public String queryAssociatedActivityByActivityName(@RequestBody Map<String, String> map) throws JsonProcessingException {
        System.out.println(map);
        ObjectMapper objectMapper = new ObjectMapper();
        List<Activity> activities = activityService.queryAssociatedActivityByActivityName(map);
        for (Activity activity : activities) {
            System.out.println("activity = " + activity);
        }
        return objectMapper.writeValueAsString(activities);
    }

}
