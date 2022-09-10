package com.crm.wrokbench.service;

import com.crm.common.utils.DateTimeUtil;
import com.crm.common.utils.UUIDUtils;
import com.crm.workbench.entity.Clue;
import com.crm.workbench.service.ClueService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 17:00
 * FileName: ClueServiceTest
 * Description: 线索业务测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml","classpath:applicationContext-dao.xml"})
public class ClueServiceTest {
    @Autowired
    private ClueService clueService;

    @Test
    public void saveClue(){
        Clue clue = new Clue();
        clue.setId(UUIDUtils.getUUID());
        clue.setCompany("北大青鸟");
        clue.setAppellation("59795c49896947e1ab61b7312bd0597c");
        clue.setFullName("段荣贵");
        clue.setJob("学生");
        clue.setEmail("duanrongui1224@163.com");
        clue.setPhone("010-88889999");
        clue.setWebsite("http://github.com");
        clue.setmPhone("15873461176");
        clue.setState("310e6a49bd8a4962b3f95a1d92eb76f4");
        clue.setSource("48512bfed26145d4a38d3616e2d2cf79");
        clue.setDescription("描述测试01");
        clue.setContactSummary("联系纪要01");
        clue.setNextContactTime("2020-10-20");
        clue.setAddress("小水镇");
        clue.setCreateTime(DateTimeUtil.convertDateCustomStringFormat(new Date()));
        clue.setCreateBy("40f6cdea0bd34aceb77492a1656d9fb3");
        int i = clueService.saveClue(clue);
        System.out.println(i);
    }
    @Test
    public void queryClueByConditionForPageTest(){
        Map<String,String> map=new HashMap<>();
        map.put("beginNo","1");
        map.put("fullName","谭志扬");
        map.put("pageSize","5");
        //System.out.println(clueService.queryClueListByConditionForPage(map));
        //System.out.println(clueService.queryCountOfByCondition(map));
        PageInfo<Clue> cluePageInfo = clueService.queryClueByConditionForPage(map);
        System.out.println(cluePageInfo.getList());
    }

}
