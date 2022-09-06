package com.crm.common.utils;

import com.crm.common.constants.Constants;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/6 8:46
 * FileName: ImportExcelUtils
 * Description: 使用poi导入Excel文件工具类
 */
public class ImportExcelUtil {

    /**
     * 获取解析Excel文件的数据
     *
     * @param file 文件
     */
    public static List<String[]> readExcel(MultipartFile file) throws Exception {
        /*获取工作簿对象*/
        Workbook workBook = getWorkBook(file);
        /*创建返回对象*/
        List<String[]> list = new ArrayList<>();
        /*页对象*/
        Sheet sheet = null;
        /*行对象*/
        Row row = null;
        if (workBook != null) {
            for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
                /*获取当前工作表*/
                sheet = workBook.getSheetAt(i);
                /*去除空白行*/
                sheet = resetSheet(sheet);
                /*获取当前sheet的开始行*/
                for (int j = 1; j <= sheet.getLastRowNum(); j++) {
                    /*获得当前行*/
                    row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    /*获取数据*/
                    list.add(getExcelRows(row));
                }
            }

        }
        return list;

    }

    /**
     * 检查文件是否合法
     *
     * @param file 文件
     */
    public static void checkFile(MultipartFile file) throws Exception {
        /*判断文件是否存在*/
        if (null == file) {
            throw new FileNotFoundException("文件不存在");
        }
        /*获取文件名*/
        String filename = file.getOriginalFilename();
        /*判断文件名是否符合文件标准*/
        if (!filename.endsWith(Constants.XLS) && !filename.endsWith(Constants.XLSX)) {
            throw new IOException(filename + "不是Excel文件");
        }
    }

    /**
     * 获取工作薄对象
     */
    public static Workbook getWorkBook(MultipartFile file) throws Exception {
        /*检查文件*/
        checkFile(file);
        /*获取文件名*/
        String filename = file.getOriginalFilename();
        /*创建workbook对象*/
        Workbook workbook = null;
        if (filename.endsWith(Constants.XLS)) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        if (filename.endsWith(Constants.XLSX)) {
            workbook = new XSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }

    /**
     * 获取每一行的数据
     */
    public static String[] getExcelRows(Row row) {
        String[] str = null;
        /*列对象*/
        Cell cell = null;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < row.getPhysicalNumberOfCells(); i++) {
            cell = row.getCell(i);
            stringBuilder.append(getStringCellValue(cell) + ",");
        }
        str = stringBuilder.toString().split(",");
        return str;
    }

    /**
     * 获取单元格的有效数据
     */
    public static String getStringCellValue(Cell cell) {
        StringBuilder sb = new StringBuilder();
        if (cell == null) {
            return "";
        }
        /*如果当前单元格内容为日期类型，需要特殊处理*/
        String dataFormatString = cell.getCellStyle().getDataFormatString();
        if (dataFormatString.equals("m/d/yy")) {
            sb.append(DateTimeUtil.convertDateCustomStringFormat(cell.getDateCellValue()));
            return sb.toString();
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        /*根据cell的类型进行判断*/
        switch (cell.getCellType()) {
            /*数字*/
            case Cell.CELL_TYPE_NUMERIC:
                sb.append(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING:
                sb.append(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                sb.append(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                sb.append(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK:
                sb.append("");
                break;
            case Cell.CELL_TYPE_ERROR:
                sb.append("非法字符");
                break;
            default:
                sb.append("未知类型");
                break;
        }
        return sb.toString();
    }

    /**
     * 去除sheet中的无效行，比如值为空但是有格式的空白行
     *
     * @param sheet
     */
    public static Sheet resetSheet(Sheet sheet) {
        CellReference cellReference = new CellReference("A4");
        boolean flag;
        Row row = null;
        for (int i = cellReference.getRow(); i <= sheet.getLastRowNum(); ) {
            row = sheet.getRow(i);
            if (row == null) {
                sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                continue;
            }
            flag = false;
            for (Cell cell : row) {
                /*如果他的列值不等于空值，则暂停循环*/
                if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                i++;
            }
            /*如果是空白行没有数据，但是有格式*/
            else {
                if (i == sheet.getLastRowNum()) {
                    /*删除当前空白行*/
                    sheet.removeRow(row);
                } else {
                    sheet.shiftRows(i + 1, sheet.getLastRowNum(), -1);
                }
            }

        }
        return sheet;
    }
}
