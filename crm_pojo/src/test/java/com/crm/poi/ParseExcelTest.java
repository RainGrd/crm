package com.crm.poi;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/5 10:42
 * FileName: ParseExcelTest
 * Description: 使用poi解析Excel文件
 */
public class ParseExcelTest {
    public static void main(String[] args) throws IOException {
        /**//*
        FileInputStream fileInputStream = new FileInputStream("D:\\lenovo\\Desktop\\activityList.xls");
        HSSFWorkbook workbook = new HSSFWorkbook(fileInputStream);
        *//*根据workbook对象获取HSSFSheet对象 页的下标，下标从零开始，一次增加*//*
        HSSFSheet sheet = workbook.getSheetAt(0);
        HSSFRow row = null;
        HSSFCell cell = null;
        for (int i = 0; i <=sheet.getLastRowNum(); i++) {
            *//*行的下标，下标从零开始，依次增加*//*
            row = sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                *//*获取列中的数据*//*
                if (cell.getCellType()== HSSFCell.CELL_TYPE_STRING) {
                    System.out.print(cell.getStringCellValue()+" ");
                }else if (cell.getCellType()== HSSFCell.CELL_TYPE_NUMERIC) {
                    System.out.print(cell.getNumericCellValue()+" ");
                } else if (cell.getCellType()== HSSFCell.CELL_TYPE_BOOLEAN) {
                    System.out.print(cell.getBooleanCellValue()+" ");
                }else if (cell.getCellType()== HSSFCell.CELL_TYPE_STRING) {
                    System.out.print(cell.getCellFormula()+" ");
                }else {
                    System.out.print(" "+" ");
                }
            }
            System.out.println();
        }*/

    }
}
