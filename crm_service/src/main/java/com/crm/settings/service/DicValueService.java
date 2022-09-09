package com.crm.settings.service;

import com.crm.settings.entity.DicValue;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 19:31
 * FileName: DicValueService
 * Description: 字典值业务层
 */
public interface DicValueService {
    /**
     * 根据类型查询字典值
     * @param TypeCode
     * @return
     */
    List<DicValue> queryDicValueByTypeCode(String TypeCode);

}
