package com.crm.workbench.service.impl;

import com.crm.common.Vo.FunnelVO;
import com.crm.common.constants.ConstantsEnum;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.User;
import com.crm.workbench.entity.*;
import com.crm.workbench.mapper.ContactsMapper;
import com.crm.workbench.mapper.CustomerMapper;
import com.crm.workbench.mapper.TransactionHistoryMapper;
import com.crm.workbench.mapper.TransactionMapper;
import com.crm.workbench.service.ActivityService;
import com.crm.workbench.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/24 7:37
 * FileName: TransactionServiceImpl
 * Description: 交易业务实现层
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private ActivityService activityService;
    @Autowired
    private TransactionHistoryMapper transactionHistoryMapper;


    @Override
    public PageInfo<Transaction> queryTransactionListByConditionForPage(Map<String, String> map) {
        int pageNum = Integer.parseInt(map.get("pageNum"));
        int pageSize = Integer.parseInt(map.get("pageSize"));
        System.out.println(pageNum);
        System.out.println(pageSize);
        PageHelper.startPage(pageNum, pageSize);
        PageInfo<Transaction> pageInfo = new PageInfo<>();
        pageInfo.setList(transactionMapper.selectTransactionListByConditionForPage(map));
        pageInfo.setTotal(transactionMapper.selectCountByCondition(map));
        return pageInfo;
    }

    @Override
    public void saveTransaction(Map<String, Object> map) {
        String customerName = (String) map.get("customerName");
        /*取出用户对象*/
        User user = (User) map.get(ConstantsEnum.SESSION_USER.getStr());
        System.out.println(user);
        /*根据客户名称查询客户*/
        Customer customer = customerMapper.selectCustomerByCustomerName(customerName);
        /*如果客户不存在的话，则创建新对象*/
        if (customer == null) {
            customer = new Customer();
            customer.setOwner(user.getId());
            customer.setName(customerName);
            customer.setId(UUIDUtils.getUUID());
            customer.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
            customer.setCreateBy(user.getId());
            customerMapper.insertCustomer(customer);
        }
        Contacts contacts = contactsMapper.selectContactsByContactsId((String) map.get("contactsId"));
        System.out.println(contacts);
        /*保存创建的交易*/
        Transaction transaction = new Transaction();
        transaction.setStage((String) map.get("stage"));
        transaction.setOwner((String) map.get("owner"));
        transaction.setNextContactTime((String) map.get("nextContactTime"));
        transaction.setName((String) map.get("name"));
        transaction.setMoney((String) map.get("money"));
        transaction.setId(UUIDUtils.getUUID());
        transaction.setExpectedDate((String) map.get("expectedDate"));
        transaction.setCustomer(customer);
        transaction.setCreateBy(user.getId());
        transaction.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        transaction.setContactSummary((String) map.get("contactSummary"));
        transaction.setContacts(contacts);
        Activity activity = activityService.queryActivityById((String) map.get("activityId"));
        System.out.println("activity = " + activity);
        transaction.setActivity(activity);
        transaction.setDescription((String) map.get("description"));
        transaction.setSource((String) map.get("source"));
        transaction.setType((String) map.get("type"));
        transactionMapper.insertTransaction(transaction);
        /*添加交易历史对象*/
        TransactionHistory transactionHistory = new TransactionHistory();
        transactionHistory.setCreateBy(user.getId());
        transactionHistory.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        transactionHistory.setExpectedDate(transaction.getExpectedDate());
        transactionHistory.setId(UUIDUtils.getUUID());
        transactionHistory.setMoney(transaction.getMoney());
        transactionHistory.setStage(transaction.getStage());
        transactionHistory.setTransaction(transaction);
        transactionHistory.setTranHistoryStatus(ConstantsEnum.TRANSACTION_HISTORY_STATUS_YES.getStr());
        transactionHistoryMapper.insertTransactionHistory(transactionHistory);
    }

    @Override
    public Transaction queryTransactionByTransactionId(String transactionId) {
        return transactionMapper.selectTransactionByTransactionId(transactionId);
    }

    @Override
    public List<FunnelVO> queryCountOfTransactionGroupByStage() {
        return transactionMapper.selectCountOfTransactionGroupByStage();
    }
}
