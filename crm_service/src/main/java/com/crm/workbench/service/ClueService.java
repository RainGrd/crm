package com.crm.workbench.service;

import com.crm.workbench.entity.Clue;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 16:58
 * FileName: ClueService
 * Description: 线索业务层
 */
public interface ClueService {

    /**
     * 插入线索
     */
    int saveClue(Clue clue);
    /**
     * 根据条件分页查询数据
     */
    PageInfo<Clue> queryClueByConditionForPage(Map<String,String> map);

    List<Clue> queryClueListByConditionForPage(Map<String,String> map);

    int queryCountOfByCondition(Map<String,String> map);

    /**
     * 根据id查询线索基本信息
     * @param id id
     * @return com.crm.workbench.entity.Clue
     */
    Clue queryClueForDetailById(String id);
}
