package com.crm.settings.mapper;

import com.crm.settings.entity.DicValue;
import com.crm.workbench.entity.Clue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DicValueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_value
     *
     * @mbggenerated Thu Sep 08 19:24:49 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_value
     *
     * @mbggenerated Thu Sep 08 19:24:49 CST 2022
     */
    int insert(DicValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_value
     *
     * @mbggenerated Thu Sep 08 19:24:49 CST 2022
     */
    int insertSelective(DicValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_value
     *
     * @mbggenerated Thu Sep 08 19:24:49 CST 2022
     */
    DicValue selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_value
     *
     * @mbggenerated Thu Sep 08 19:24:49 CST 2022
     */
    int updateByPrimaryKeySelective(DicValue record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_dic_value
     *
     * @mbggenerated Thu Sep 08 19:24:49 CST 2022
     */
    int updateByPrimaryKey(DicValue record);

    /**
     * 根据类型选择数据字典值
     * @param typeCode string
     * @return java.util.List
     */
    List<DicValue> selectDicValueByTypeCode(String typeCode);


}