package com.crm.test;

import org.junit.Test;

import java.util.UUID;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/8/26 16:56
 * FileName: UUIDTest
 * Description: 测试UUID每次都要生成不重复的32位字符串
 */
public class UUIDTest {
    public static void main(String[] args) {
        String uuidStr = UUID.randomUUID().toString().replace("-","");
        System.out.println(uuidStr);
    }
}
