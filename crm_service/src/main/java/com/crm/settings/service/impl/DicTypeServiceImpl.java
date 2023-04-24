package com.crm.settings.service.impl;

import com.crm.settings.entity.DictionaryType;
import com.crm.settings.mapper.DictionaryTypeMapper;
import com.crm.settings.service.DicTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname DicTypeServiceImpl
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/7 17:06
 * @Author RainGrd
 */
@Service
public class DicTypeServiceImpl implements DicTypeService {

    @Autowired
    private DictionaryTypeMapper dictionaryTypeMapper;

    @Override
    public List<DictionaryType> queryDictionaryTypeList() {
        return dictionaryTypeMapper.selectDictionTypeList();
    }

    @Override
    public int saveDicType(DictionaryType dictionaryType) {
        //判断主键是否重复
        DictionaryType type = dictionaryTypeMapper.selectByPrimaryKey(dictionaryType.getCode());
        return type != null ? 0 : dictionaryTypeMapper.insertDicType(dictionaryType);
    }

    @Override
    public List<String> queryDictionaryTypeCodeList() {
        return dictionaryTypeMapper.selectCodeList();
    }

    @Override
    public int editDicType(DictionaryType dictionaryType) {
        return dictionaryTypeMapper.updateByDictionaryType(dictionaryType);
    }

    @Override
    public DictionaryType queryDicTypeByCode(String code) {
        return dictionaryTypeMapper.selectByPrimaryKey(code);
    }

    @Override
    public int deleteDicTypeByCode(String[] codes) {
        return dictionaryTypeMapper.deleteDicTypeByCodes(codes);
    }


}
