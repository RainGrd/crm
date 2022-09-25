package com.crm.wrokbench.service;

import com.crm.workbench.entity.Contacts;
import com.crm.workbench.service.ContactsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/25 10:03
 * FileName: ContactsServiceTest
 * Description: 联系人业务测试层
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-dao.xml", "classpath:applicationContext-service.xml"})
public class ContactsServiceTest {

    @Autowired
    private ContactsService contactsService;

    @Test
    public void queryContactsListByContactsNameTest() {
        List<Contacts> contactsList = contactsService.queryContactsListByContactsName("段");
        for (Contacts contacts : contactsList) {
            System.out.println("contacts = " + contacts);
        }
    }
}
