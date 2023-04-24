package com.crm.settings.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

/**
 * @Classname DicTypeServiceTest
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/8 14:47
 * @Author RainGrd
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml","classpath:applicationContext-dao.xml"})
public class DicTypeServiceTest {

    @Autowired
    private DicTypeService dicTypeService;

    @Test
    public void queryDictionaryTypeCodeListTest(){
        List<String> strings = dicTypeService.queryDictionaryTypeCodeList();
        System.out.println("strings = " + strings);
    }

}
