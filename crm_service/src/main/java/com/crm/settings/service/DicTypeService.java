package com.crm.settings.service;

import com.crm.settings.entity.DictionaryType;

import java.util.List;

/**
 * @Classname DicTypeService
 * @Description TODO
 * @Version 1.0.0
 * @Date 2023/3/7 17:06
 * @Author RainGrd
 */
public interface DicTypeService {
   List<DictionaryType> queryDictionaryTypeList();

    int saveDicType(DictionaryType dictionaryType);

    List<String> queryDictionaryTypeCodeList();

    int editDicType(DictionaryType dictionaryType);

    DictionaryType queryDicTypeByCode(String code);

    int deleteDicTypeByCode(String[] code);
}
