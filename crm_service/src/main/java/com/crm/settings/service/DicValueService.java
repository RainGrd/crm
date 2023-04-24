package com.crm.settings.service;

import com.crm.settings.entity.DicValue;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

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

    PageInfo<DicValue> queryDicValueForPage(String beginNo,String pageSize);

    int saveDivValue(DicValue dicValue);

    int editDicValue(DicValue dicValue);

    DicValue queryDicValueById(String id);

    int removeDicValueByIds(String[] ids);
}
