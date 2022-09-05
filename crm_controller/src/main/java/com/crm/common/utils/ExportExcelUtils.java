package com.crm.common.utils;

import lombok.SneakyThrows;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/4 19:45
 * FileName: ExportExcelUtils
 * Description: 使用poi导出Excel文件
 */
public class ExportExcelUtils<T> {
    /**
     * 创建Excel文件并将数据写入ExCel文件，并返回到浏览器中
     *
     * @param headers  标题栏
     * @param dataset  数据
     * @param fileName 文件名称
     * @param response 返回数据对象
     */
    @SneakyThrows
    public void exportExcel(String[] headers, Collection<T> dataset, String fileName, HttpServletResponse response) {
        /*创建Excel文件*/
        HSSFWorkbook workbook = new HSSFWorkbook();
        /*生成表格(页)*/
        HSSFSheet sheet = workbook.createSheet();
        /*设置表格默认列宽度为15个字节*/
        sheet.setDefaultColumnWidth(20);
        /*生成标题行*/
        HSSFRow row = sheet.createRow(0);
        Cell cell = null;
        /*遍历headers数组，获取标题*/
        for (int i = 0; i < headers.length; i++) {
            /*生成格*/
            cell = row.createCell(i);
            HSSFRichTextString hssfRichTextString = new HSSFRichTextString(headers[i]);
            cell.setCellValue(hssfRichTextString);
        }
        /*遍历数据行*/
        Iterator<T> iterator = dataset.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T next = iterator.next();
            /*使用反射，根据javabean对象的先后顺序，动态调用getXXX()方法获得属性值*/
            Field[] declaredFields = next.getClass().getDeclaredFields();
            for (int i = 0; i < declaredFields.length; i++) {
                cell = row.createCell(i);
                Field field = declaredFields[i];
                /*获取字段名*/
                String fieldName = field.getName();
                System.out.println(fieldName);
                /*获取方法名*/
                String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                Method method = next.getClass().getMethod(getMethodName, new Class[]{});
                /*执行方法*/
                Object invoke = method.invoke(next, new Object[]{});
                /*判断值得类型进行强制类型转换*/
                String textValue = null;
                /*其他数据类型都当作字符串简单处理*/
                if (invoke != null && invoke != "") {
                    textValue = invoke.toString();
                }
                if (textValue != null) {
                    HSSFRichTextString hssfRichTextString = new HSSFRichTextString(textValue);
                    cell.setCellValue(hssfRichTextString);
                }
            }

        }
        /*调用返回对象方法，将数据输出到浏览器*/
        getExportedFile(workbook,fileName,response);
    }

    public void getExportedFile(HSSFWorkbook workbook, String name, HttpServletResponse response) throws Exception {
        BufferedOutputStream bufferedOutputStream = null;
        try {
            /*处理文件名，拼接文件后缀名*/
            String fileName = name + ".xls";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
            workbook.write(bufferedOutputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedOutputStream != null) {
                bufferedOutputStream.close();
            }
        }


    }
}
