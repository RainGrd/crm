package com.crm.workbench.service.impl;

import com.crm.workbench.entity.ClueActivityRelation;
import com.crm.workbench.mapper.ClueActivityRelationMapper;
import com.crm.workbench.service.ClueActivityRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/15 11:07
 * FileName: ClueActivityRelationServiceImpl
 * Description:关联市场活动业务实现层
 */
@Service("clueActivityRelationService")
public class ClueActivityRelationServiceImpl implements ClueActivityRelationService {
    @Autowired
    private ClueActivityRelationMapper clueActivityRelationMapper;

    @Override
    public int saveClueActivityRelationByList(List<ClueActivityRelation> activityRelationList) {
        return clueActivityRelationMapper.insertClueActivityRelationByList(activityRelationList);
    }

    @Override
    public int deleteClueActivityRelationByClueIdActivityId(ClueActivityRelation clueActivityRelation) {
        return clueActivityRelationMapper.deleteClueRelationByClueIdActivityId(clueActivityRelation);
    }
}
