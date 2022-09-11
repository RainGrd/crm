package com.crm.workbench.service.impl;

import com.crm.workbench.entity.Clue;
import com.crm.workbench.mapper.ClueMapper;
import com.crm.workbench.service.ClueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
