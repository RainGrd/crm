package com.crm.workbench.service.impl;

import com.crm.workbench.entity.ClueRemark;
import com.crm.workbench.mapper.ClueRemarkMapper;
import com.crm.workbench.service.ClueRemarkService;
import com.crm.workbench.service.ClueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/11 14:31
 * FileName: ClueRemarkServiceImpl
 * Description: 线索备注业务实习层
 */
@Service("clueRemarkService")
public class ClueRemarkServiceImpl implements ClueRemarkService {

    @Autowired
    private ClueRemarkMapper clueRemarkMapper;

    @Override
    public List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId) {
        return clueRemarkMapper.selectClueRemarkForDetailByClueId(clueId);
    }
}
