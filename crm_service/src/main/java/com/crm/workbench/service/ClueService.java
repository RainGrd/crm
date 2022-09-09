package com.crm.workbench.service;

import com.crm.workbench.entity.Clue;

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
}
