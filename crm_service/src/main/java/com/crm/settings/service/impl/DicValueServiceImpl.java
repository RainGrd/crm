package com.crm.settings.service.impl;

import com.crm.settings.entity.DicValue;
import com.crm.settings.mapper.DicValueMapper;
import com.crm.settings.service.DicValueService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/9/8 19:34
 * FileName: DicValueServiceimpl
 * Description: 字典值业务实现层
 */
@Service("dicValueService")
public class DicValueServiceImpl implements DicValueService {

    @Autowired
    private DicValueMapper dicValueMapper;

    @Override
    public List<DicValue> queryDicValueByTypeCode(String TypeCode) {
        return dicValueMapper.selectDicValueByTypeCode(TypeCode);
    }

    @Override
    public PageInfo<DicValue> queryDicValueForPage(String beginNo,String pageSize) {
        PageHelper.startPage(Integer.parseInt(beginNo), Integer.parseInt(pageSize));
        PageInfo<DicValue> dicValuePageInfo = new PageInfo<>();
        dicValuePageInfo.setList(dicValueMapper.selectDicValueAll());
        dicValuePageInfo.setTotal(dicValueMapper.selectCountAll());
        return dicValuePageInfo;
    }

    @Override
    public int saveDivValue(DicValue dicValue) {
        return dicValueMapper.insertDicValue(dicValue);
    }

    @Override
    public int editDicValue(DicValue dicValue) {
        return dicValueMapper.updateById(dicValue);
    }


    @Override
    public DicValue queryDicValueById(String id) {
        return dicValueMapper.selectDicValueById(id);
    }

    @Override
    public int removeDicValueByIds(String[] ids) {
        return dicValueMapper.deleteDicValueByIds(ids);
    }
}
