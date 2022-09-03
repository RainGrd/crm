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
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
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
    /**
     * 文件下载
     */
    @RequestMapping("workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response){
        /*1.设置响应信息*/
        response.setContentType("application/octet-stream;charset=UTF-8");
        /*2.获取输出流*/
        try {
            OutputStream outputStream = response.getOutputStream();
            /*激活文件下载窗口*/
            response.addHeader("Content-Disposition","attachment;filename=mystudentList.xls");
            /*读取Excel文件，输出到浏览器*/
            FileInputStream fileInputStream = new FileInputStream("D:\\lenovo\\studentList.xls");
            byte[] buff=new byte[256];
            int len=0;
            /*循环遍历*/
            while ((len=fileInputStream.read(buff))!=-1){
                outputStream.write(buff,0,len);
            }
            /*关闭流*/
            fileInputStream.close();
            /*冲洗流*/
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 批量导出市场活动
     */
    @RequestMapping("workbench/activity/exportAllActivitys.do")
    public void exportAllActivitys(HttpServletResponse response){
        List<Activity> activities = activityService.queryActivityList();
        System.out.println(activities);
        /*创建WorkBook对象*/
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell= row.createCell(1);
        cell.setCellValue("所有者");
        cell= row.createCell(2);
        cell.setCellValue("名称");
        cell= row.createCell(3);
        cell.setCellValue("开始日期");
        cell= row.createCell(4);
        cell.setCellValue("结束日期");
        cell= row.createCell(5);
        cell.setCellValue("成本");
        cell= row.createCell(6);
        cell.setCellValue("描述");
        cell= row.createCell(7);
        cell.setCellValue("创建时间");
        cell= row.createCell(8);
        cell.setCellValue("创建者");
        cell= row.createCell(9);
        cell.setCellValue("修改时间");
        cell= row.createCell(10);
        cell.setCellValue("修改者");

        if(activities.size()!=0){
            Activity activity=null;
            for (int i = 0; i < activities.size(); i++) {
                activity = activities.get(i);
                /*创建行数*/
                row=sheet.createRow(i+1);
                /*每一行都有十一列*/
                cell=row.createCell(0);
                cell.setCellValue(activity.getId());
                cell= row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell= row.createCell(2);
                cell.setCellValue(activity.getName());
                cell= row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell= row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell= row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell= row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell= row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell= row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell= row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell= row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        response.setContentType("application/out");
    }
}
