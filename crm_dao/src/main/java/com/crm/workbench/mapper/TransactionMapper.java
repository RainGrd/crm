package com.crm.workbench.mapper;

import com.crm.workbench.entity.Transaction;

public interface TransactionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    int insert(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    int insertSelective(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    Transaction selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    int updateByPrimaryKeySelective(Transaction record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction
     *
     * @mbggenerated Fri Sep 16 17:06:39 CST 2022
     */
    int updateByPrimaryKey(Transaction record);

    /**
     * 保存创建的交易
     */
    int insertTransaction(Transaction transaction);
}