package com.crm.workbench.service;

import com.crm.workbench.entity.Contacts;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/25 9:59
 * FileName: ContactsService
 * Description: 联系人业务层
 */
public interface ContactsService {
    /**
     * 根据联系人名称查询联系人对象
     * @return
     */
    List<Contacts> queryContactsListByContactsName(String contactsName);
}
