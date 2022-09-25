package com.crm.workbench.service.impl;

import com.crm.workbench.entity.Contacts;
import com.crm.workbench.mapper.ContactsMapper;
import com.crm.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/25 10:00
 * FileName: ContactsServiceImpl
 * Description: 联系人业务实现层
 */
@Service("contactsService")
public class ContactsServiceImpl implements ContactsService {

    @Autowired
    private ContactsMapper contactsMapper;

    @Override
    public List<Contacts> queryContactsListByContactsName(String contactsName) {
        return contactsMapper.selectContactsListByContactsName(contactsName);
    }
}
