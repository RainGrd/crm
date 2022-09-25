package com.crm.workbench.activity.controller;

import com.crm.common.Vo.PageBean;
import com.crm.common.constants.Constants;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.ExportExcelUtil;
import com.crm.common.utils.ImportExcelUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.User;
import com.crm.settings.service.UserService;
import com.crm.workbench.entity.Activity;
import com.crm.workbench.service.ActivityRemarkService;
import com.crm.workbench.service.ActivityService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
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
    @Autowired
    private HttpSession session;
    @Autowired
    private ActivityRemarkService activityRemarkService;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
    public String saveCreateActivity(Activity activity) throws JsonProcessingException {
        User user = (User) session.getAttribute(Constants.SESSION_USER);
        /*封装参数*/
        activity.setId(UUIDUtils.getUUID());
        activity.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
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
    public Object updateActivityById(@RequestBody Activity activity) {
        PageBean pageBean = new PageBean();
        try {
            int updateActivityById = activityService.updateActivityById(activity);
            if (updateActivityById > 0) {
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
     * 文件下载
     */
    @RequestMapping("workbench/activity/fileDownload.do")
    public void fileDownload(HttpServletResponse response) {
        /*1.设置响应信息*/
        response.setContentType("application/octet-stream;charset=UTF-8");
        /*2.获取输出流*/
        try {
            OutputStream outputStream = response.getOutputStream();
            /*激活文件下载窗口*/
            response.addHeader("Content-Disposition", "attachment;filename=mystudentList.xls");
            /*读取Excel文件，输出到浏览器*/
            FileInputStream fileInputStream = new FileInputStream("D:\\lenovo\\studentList.xls");
            byte[] buff = new byte[256];
            int len = 0;
            /*循环遍历*/
            while ((len = fileInputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, len);
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
    public void exportAllActivitys(HttpServletResponse response) throws Exception {
        List<Activity> activities = activityService.queryActivityList();
        System.out.println(activities);
        /*        *//*创建WorkBook对象*//*
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("市场活动列表");
        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);*/
        String[] headers = {"ID", "所有者", "名称", "开始日期", "结束日期", "成本", "描述", "创建时间", "创建者", "修改时间", "修改者", "修改状态"};
        String fileName = "activityList";
       /* cell.setCellValue("ID");
        cell = row.createCell(1);
        cell.setCellValue("所有者");
        cell = row.createCell(2);
        cell.setCellValue("名称");
        cell = row.createCell(3);
        cell.setCellValue("开始日期");
        cell = row.createCell(4);
        cell.setCellValue("结束日期");
        cell = row.createCell(5);
        cell.setCellValue("成本");
        cell = row.createCell(6);
        cell.setCellValue("描述");
        cell = row.createCell(7);
        cell.setCellValue("创建时间");
        cell = row.createCell(8);
        cell.setCellValue("创建者");
        cell = row.createCell(9);
        cell.setCellValue("修改时间");
        cell = row.createCell(10);
        cell.setCellValue("修改者");
        *//*按断条件是否符合*//*
        if (activities != null && activities.size() > 0) {
            Activity activity = null;
            for (int i = 0; i < activities.size(); i++) {
                activity = activities.get(i);
                *//*创建行数*//*
                row = sheet.createRow(i + 1);
                *//*每一行都有十一列*//*
                cell = row.createCell(0);
                cell.setCellValue(activity.getId());
                cell = row.createCell(1);
                cell.setCellValue(activity.getOwner());
                cell = row.createCell(2);
                cell.setCellValue(activity.getName());
                cell = row.createCell(3);
                cell.setCellValue(activity.getStartDate());
                cell = row.createCell(4);
                cell.setCellValue(activity.getEndDate());
                cell = row.createCell(5);
                cell.setCellValue(activity.getCost());
                cell = row.createCell(6);
                cell.setCellValue(activity.getDescription());
                cell = row.createCell(7);
                cell.setCellValue(activity.getCreateTime());
                cell = row.createCell(8);
                cell.setCellValue(activity.getCreateBy());
                cell = row.createCell(9);
                cell.setCellValue(activity.getEditTime());
                cell = row.createCell(10);
                cell.setCellValue(activity.getEditBy());
            }
        }
        *//*吧生成的Excel文件下载到客户端中*//*
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=activityList.xls");
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        *//*冲洗流 注意事项，response对象属于Tomcat服务器，不需要手动关闭流，Tomcat运行完毕后会自动关闭*//*
        outputStream.flush();*/
        ExportExcelUtil<Activity> excelUtils = new ExportExcelUtil<>();
        excelUtils.exportExcel(headers, activities, fileName, response);
    }

    /**
     * 选择导出市场活动
     */
    @RequestMapping("/workbench/activity/exportMarketingActivities.do")
    public void exportMarketingActivities(@RequestParam("ids") String[] ids, HttpServletResponse response) throws Exception {
        List<Activity> activities = activityService.queryActivityByIds(ids);
        String[] headers = {"ID", "所有者", "名称", "开始日期", "结束日期", "成本", "描述", "创建时间", "创建者", "修改时间", "修改者", "修改状态"};
        String fileName = "activityList";
        ExportExcelUtil<Activity> excelUtils = new ExportExcelUtil<>();
        excelUtils.exportExcel(headers, activities, fileName, response);
    }

    /**
     * @param userName
     * @param myFile
     * @return
     */
    @RequestMapping("/workbench/activity/fileUpLoad.do")
    @ResponseBody
    public Object fileUpLoad(String userName, MultipartFile myFile, HttpServletRequest request) throws IOException {
        String originalFilename = myFile.getOriginalFilename();
        /*把文件在服务指定的目录中生成一个同样的文件*/
        String realPath = request.getServletContext().getRealPath("/crm_controller/src/main/webapp/multipartFile/");
        System.out.println(realPath);
        File file = null;
        if (originalFilename != null) {
            file = new File(request.getServletContext().getRealPath("/multipartFile/"), originalFilename);
            /*如果没有文件夹得
             * */
            if (!file.exists()) {
                file.mkdir();
            }
            myFile.transferTo(file);
        }
        PageBean pageBean = new PageBean();
        pageBean.setCode(Constants.Page_BEAN_CODE_SUCCESS);
        pageBean.setMessage("成功！");
        /*返回响应信息*/
        return pageBean;
    }

    /**
     * 导入市场活动
     */
    @RequestMapping("/workbench/activity/importExcelActivity.do")
    @ResponseBody
    public Object importExcelActivity(MultipartFile multipartFile, HttpServletRequest request) throws Exception {
        PageBean pageBean = new PageBean();
        User user = (User) session.getAttribute(Constants.SESSION_USER);

        /*File file=new File(request.getServletContext().getRealPath("/file/"),multipartFile.getOriginalFilename());
        if (!file.exists()) {
            file.mkdir();
        }
        *//*复制到当前项目的目录下*//*
        multipartFile.transferTo(file);*/
        List<String[]> list = ImportExcelUtil.readExcel(multipartFile);
        System.out.println(list);
        try {
            int saveCreateActivityByList = activityService.saveCreateActivityByList(list, user);
            if (saveCreateActivityByList > 0) {
                pageBean.setCode(Constants.Page_BEAN_CODE_SUCCESS);
                pageBean.setData(saveCreateActivityByList);
            } else {
                pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
                pageBean.setData(saveCreateActivityByList);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            pageBean.setCode(Constants.Page_BEAN_CODE_FAIL);
            pageBean.setMessage("系统忙，稍后重试...");
        }
        return pageBean;
    }

    /**
     * 跳转活动详细页面
     */
    @RequestMapping("/workbench/activity/detailActivity.do")
    public ModelAndView detailActivity(String id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activity", activityService.queryActivityForDetail(id));
        modelAndView.addObject("remarkList", activityRemarkService.queryActivityRemarkForDetailByActivityId(id));
        modelAndView.setViewName("/workbench/activity/detail");
        return modelAndView;
    }
}
