package com.crm.poi;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/5 10:42
 * FileName: ParseExcelTest
 * Description: 使用poi解析Excel文件
 */
public class ParseExcelTest {
    public static void main(String[] args) throws FileNotFoundException {
        /**/
        FileInputStream fileInputStream = new FileInputStream("D:\\lenovo\\Desktop\\activityList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook();
        /*根据workbook对象获取HSSFSheet对象 页的下标，下标从零开始，一次增加*/
        HSSFSheet sheet = workbook.getSheetAt(0);
        for (int i = 0; i < 10; i++) {
            
        }

    }
}
