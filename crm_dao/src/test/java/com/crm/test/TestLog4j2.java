package com.crm.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/25 15:12
 * FileName: TestLog4j2
 * Description: 测试log4j2
 */
public class TestLog4j2 {
    static Logger logger= LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);
    public static void main(String[] args) {
        logger.trace("trace message");
        logger.debug("debug message");
        logger.info("info message");
        logger.warn("warn message");
        logger.error("error message");
        logger.fatal("fatal message");
        System.out.println("Hello World!");
        //手写Java输出文件夹名
        String FileName="D:\\materials\\crm";

        File file = new File(FileName);
        System.out.println(file.getName());
        for (File listFile : file.listFiles()) {
            System.out.println(listFile.getName());
        }

    }
}
