package com.crm.workbench.service.impl;

import com.crm.common.constants.Constants;
import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.settings.entity.User;
import com.crm.workbench.entity.*;
import com.crm.workbench.mapper.*;
import com.crm.workbench.service.ClueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 16:59
 * FileName: ClueServiceImpl
 * Description: 线索业务层实现类
 */
@Service("clueService")
public class ClueServiceImpl implements ClueService {
    @Autowired
    private ClueMapper clueMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ContactsMapper contactsMapper;
    @Autowired
    private ClueRemarkMapper clueRemarkMapper;
    @Autowired
    private CustomerRemarkMapper customerRemarkMapper;
    @Autowired
    private ContactsRemarkMapper contactsRemarkMapper;
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;
    @Autowired
    private ContactsActivityRelationMapper contactsActivityRelationMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionRemarkMapper transactionRemarkMapper;

    @Override
    public int saveClue(Clue clue) {
        return clueMapper.insertCreateClue(clue);
    }

    @Override
    public PageInfo<Clue> queryClueByConditionForPage(Map<String, String> map) {
        int beginNo = Integer.parseInt(map.get("beginNo"));
        System.out.println(beginNo);
        int pageSize = Integer.parseInt(map.get("pageSize"));
        System.out.println(pageSize);
        PageHelper.startPage(beginNo, pageSize);
        PageInfo<Clue> PageInfo = new PageInfo<>();
        PageInfo.setList(clueMapper.selectClueListByConditionForPage(map));
        PageInfo.setTotal(clueMapper.selectCountOfClueByCondition(map));
        return PageInfo;
    }

    @Override
    public List<Clue> queryClueListByConditionForPage(Map<String, String> map) {
        return clueMapper.selectClueListByConditionForPage(map);
    }

    @Override
    public int queryCountOfByCondition(Map<String, String> map) {
        return clueMapper.selectCountOfClueByCondition(map);
    }

    @Override
    public Clue queryClueForDetailById(String id) {
        return clueMapper.selectClueForDetailById(id);
    }

    @Override
    public void saveConvert(Map<String, Object> map) {
        /*取出数据*/
        String activityId = (String) map.get("activityId");
        String amountOfMoney = (String) map.get("amountOfMoney");
        String tradeName = (String) map.get("tradeName");
        String expectedClosingDate = (String) map.get("expectedClosingDate");
        String stage = (String) map.get("stage");
        String clueId = (String) map.get("clueId");
        /*判断是否需要交易*/
        Boolean isCreateTran = (Boolean) map.get("isCreateTran");
        User user = (User) map.get(Constants.SESSION_USER);
        /*根据id查询线索信息*/
        Clue clue = clueMapper.selectByClueId(clueId);
        System.out.println(clue);
        /*把线索中有关公司的信息转换到客户表中*/
        //封装客户表
        Customer customer = new Customer();
        customer.setAddress(clue.getAddress());
        customer.setContactSummary(clue.getContactSummary());
        customer.setCreateBy(user.getId());
        customer.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        customer.setId(UUIDUtils.getUUID());
        customer.setName(clue.getFullName());
        customer.setOwner(user.getId());
        customer.setPhone(clue.getPhone());
        customer.setWebsite(clue.getWebsite());
        System.out.println(customer);
        customerMapper.insertCustomer(customer);
        /*把该线索中有关个人的信息转换到联系人表中*/
        //封装联系人对象
        Contacts contacts = new Contacts();
        contacts.setAddress(clue.getAddress());
        contacts.setAppellation(clue.getAppellation());
        contacts.setContactSummary(clue.getContactSummary());
        contacts.setCreateBy(user.getId());
        contacts.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        contacts.setCustomerId(customer.getId());
        contacts.setDescription(clue.getDescription());
        contacts.setEmail(clue.getEmail());
        contacts.setFullName(clue.getFullName());
        contacts.setId(UUIDUtils.getUUID());
        contacts.setJob(clue.getJob());
        contacts.setmPhone(clue.getmPhone());
        contacts.setNextContactTime(clue.getNextContactTime());
        contacts.setOwner(user.getId());
        contacts.setSource(clue.getSource());
        /*插入联系人对象*/
        contactsMapper.insertContacts(contacts);
        //根据clueID查询该线索下相关联的备注
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectClueRemarkByClueId(clueId);
        //判断备注是否为空
        if (clueRemarks != null && clueRemarks.size() != 0) {
            CustomerRemark customerRemark = null;
            List<CustomerRemark> customerRemarkList = new ArrayList<>();
            ContactsRemark contactsRemark = null;
            List<ContactsRemark> contactsRemarkList = new ArrayList<>();
            for (ClueRemark clueRemark : clueRemarks) {
                /*封装客户备注对象*/
                customerRemark = new CustomerRemark();
                customerRemark.setCreateBy(clueRemark.getCreateBy());
                customerRemark.setCreateTime(clueRemark.getCreateTime());
                customerRemark.setCustomerId(customer.getId());
                customerRemark.setEditBy(clueRemark.getEditBy());
                customerRemark.setEditFlag(clueRemark.getEditFlag());
                customerRemark.setNoteContent(clueRemark.getNoteContent());
                customerRemark.setId(UUIDUtils.getUUID());
                customerRemarkList.add(customerRemark);
                /*封装联系人备注对象*/
                contactsRemark=new ContactsRemark();
                contactsRemark.setId(UUIDUtils.getUUID());
                contactsRemark.setCreateBy(clueRemark.getCreateBy());
                contactsRemark.setCreateTime(clueRemark.getCreateTime());
                contactsRemark.setContactsId(contacts.getId());
                contactsRemark.setEditBy(clueRemark.getEditBy());
                contactsRemark.setEditFlag(clueRemark.getEditFlag());
                contactsRemark.setEditTime(clueRemark.getEditTime());
                contactsRemark.setNoteContent(clueRemark.getNoteContent());
                contactsRemarkList.add(contactsRemark);
            }
            //执行方法
            contactsRemarkMapper.insertContactsRemarkByList(contactsRemarkList);
            customerRemarkMapper.insertCustomerRemarkByList(customerRemarkList);
        }
        /*根据clueId查询线索与市场活动的关联关系*/
        List<ClueActivityRelation> clueActivityRelations = clueActivityRelationMapper.clueActivityRelationList(clueId);
        /*把线索和市场活动的关联关系转换到联系人和市场活动的关联关系表中*/
        if (clueActivityRelations != null && clueActivityRelations.size() != 0) {
            ContactsActivityRelation contactsActivityRelation = null;
            List<ContactsActivityRelation> contactsActivityRelationList = new ArrayList<>();
            for (ClueActivityRelation clueActivityRelation : clueActivityRelations) {
                contactsActivityRelation = new ContactsActivityRelation();
                //封装对象
                contactsActivityRelation.setId(UUIDUtils.getUUID());
                contactsActivityRelation.setActivityId(clueActivityRelation.getActivityId());
                contactsActivityRelation.setContactsId(contacts.getId());
                contactsActivityRelationList.add(contactsActivityRelation);
            }
            contactsActivityRelationMapper.insertContactsActivityRelationByContactsActivityRelation(contactsActivityRelationList);
        }

        /*需要创建交易*/
        if (isCreateTran) {
            /*如果需要创建交易，则需要往交易表中添加一条记录*/
            Transaction transaction = new Transaction();
            transaction.setActivityId(activityId);
            transaction.setContactsId(contacts.getId());
            transaction.setCreateBy(user.getId());
            transaction.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
            transaction.setCustomerId(customer.getId());
            transaction.setExpectedDate(expectedClosingDate);
            transaction.setId(UUIDUtils.getUUID());
            transaction.setName(tradeName);
            transaction.setStage(stage);
            transaction.setOwner(user.getId());
            transaction.setMoney(amountOfMoney);
            transactionMapper.insertTransaction(transaction);
            /*如果需要交易，则需要将该线索下的所有备注转换到交易备注表中*/
            if (clueRemarks != null && clueRemarks.size() != 0) {
                TransactionRemark transactionRemark = null;
                List<TransactionRemark> transactionRemarks = new ArrayList<>();
                for (ClueRemark clueRemark : clueRemarks) {
                    transactionRemark = new TransactionRemark();
                    transactionRemark.setCreateBy(clueRemark.getCreateBy());
                    transactionRemark.setCreateTime(clueRemark.getCreateTime());
                    transactionRemark.setEditBy(clueRemark.getEditBy());
                    transactionRemark.setEditFlag(clueRemark.getEditFlag());
                    transactionRemark.setEditTime(clueRemark.getEditTime());
                    transactionRemark.setId(UUIDUtils.getUUID());
                    transactionRemark.setNoteContent(clueRemark.getNoteContent());
                    transactionRemark.setTranId(transaction.getId());
                    System.out.println(transactionRemark);
                    transactionRemarks.add(transactionRemark);
                }
                transactionRemarkMapper.insertTransactionRemark(transactionRemarks);
            }
        }
        /*删除显示该线索下的所有备注*/
        clueRemarkMapper.deleteClueRemarkByClueId(clueId);
        /*删除该线索和市场活动的关联关系*/
        clueActivityRelationMapper.deleteClueActivityRelationByClueId(clueId);
        /*删除该线索*/
        clueMapper.deleteClueByClueId(clueId);
    }
}
