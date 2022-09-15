package com.crm.wrokbench.service;

import com.crm.common.utils.UUIDUtils;
import com.crm.workbench.entity.ClueActivityRelation;
import com.crm.workbench.service.ClueActivityRelationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/15 11:09
 * FileName: ClueActivityRelationServiceTest
 * Description: 关联市场活动业务实现层测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-service.xml", "classpath:applicationContext-dao.xml"})
public class ClueActivityRelationServiceTest {
    @Autowired
    private ClueActivityRelationService activityRelationService;

    @Test
    public void saveClueActivityRelationByListTest() {
        List<ClueActivityRelation> clueActivityRelations = new ArrayList<>();
        ClueActivityRelation clueActivityRelation = new ClueActivityRelation();
        clueActivityRelation.setId(UUIDUtils.getUUID());
        clueActivityRelation.setClueId("55b2b44369374a11a098b4ea1fa101b7");
        clueActivityRelation.setActivityId("48391170784c46c9b0b2f86943586709");
        clueActivityRelations.add(clueActivityRelation);
        System.out.println(activityRelationService.saveClueActivityRelationByList(clueActivityRelations));
    }

}
