package com.crm.settings.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Alias("dictionaryType")
@Data
public class DictionaryType {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_dic_type.code
     *
     * @mbggenerated Tue Mar 07 15:30:14 CST 2023
     */
    private String code;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_dic_type.name
     *
     * @mbggenerated Tue Mar 07 15:30:14 CST 2023
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tbl_dic_type.description
     *
     * @mbggenerated Tue Mar 07 15:30:14 CST 2023
     */
    private String description;
}