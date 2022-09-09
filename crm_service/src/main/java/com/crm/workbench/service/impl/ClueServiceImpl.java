package com.crm.workbench.service.impl;

import com.crm.workbench.entity.Clue;
import com.crm.workbench.mapper.ClueMapper;
import com.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
