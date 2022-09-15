package com.crm.workbench.service;

import com.crm.workbench.entity.ClueActivityRelation;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/15 11:06
 * FileName: ClueActiivityRelationService
 * Description: 关联市场活动业务层
 */
public interface ClueActivityRelationService {

    int saveClueActivityRelationByList(List<ClueActivityRelation> activityRelationList);
}
