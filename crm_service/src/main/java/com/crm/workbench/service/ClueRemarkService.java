package com.crm.workbench.service;

import com.crm.workbench.entity.ClueRemark;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/11 14:29
 * FileName: ClueRemarkService
 * Description: 线索备注业务层
 */
public interface ClueRemarkService {
    /**
     * 根据线索id查询线索备注id
     *
     * @param clueId 线索id
     * @return java.util.List
     */
    List<ClueRemark> queryClueRemarkForDetailByClueId(String clueId);
}
