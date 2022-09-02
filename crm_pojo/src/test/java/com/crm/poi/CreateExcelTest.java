package com.crm.poi;

import org.apache.poi.hssf.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/1 14:57
 * FileName: CreateExcel
 * Description: 使用pot创建excel文件测试类
 */
public class CreateExcelTest {
    public static void main(String[] args) throws IOException {
        /*创建HSSFWorkbook,因为一个Excel文件*/
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        /*创建workBook创建sheet对象，对应excel文件的一页*/
        HSSFSheet sheet = hssfWorkbook.createSheet("学生列表");
        /*创建行*/
        HSSFRow row = sheet.createRow(0);
        /*创建列*/
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("学号");
        cell = row.createCell(1);
        cell.setCellValue("姓名");
        cell = row.createCell(2);
        cell.setCellValue("年龄");
        /*创建hssfCellStyle对象*/
        HSSFCellStyle cellStyle = hssfWorkbook.createCellStyle();
        /*使用sheet创建十个HSSFROW对象，对应sheet中的10行*/
        for (int i = 1; i <= 10; i++) {
            row = sheet.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(100 + i);
            cell = row.createCell(1);
            cell.setCellValue("NAME" + i);
            cell = row.createCell(2);
            cell.setCellValue(20 + i);
        }

        /*
         * 调用工具函数生层Excel文件
         * 目录必须手动创建，文件如果不存在，插件会自动创建
         * */

        OutputStream outputStream = new FileOutputStream("D:\\lenovo\\studentList.xls");
        hssfWorkbook.write(outputStream);
        /*释放资源*/
        outputStream.flush();
        outputStream.close();
        hssfWorkbook.close();
        System.out.println("ok");
    }
}
