package com.crm.workbench.mapper;

import com.crm.workbench.entity.TransactionRemark;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TransactionRemarkMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction_remark
     *
     * @mbggenerated Fri Sep 16 17:09:32 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction_remark
     *
     * @mbggenerated Fri Sep 16 17:09:32 CST 2022
     */
    int insert(TransactionRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction_remark
     *
     * @mbggenerated Fri Sep 16 17:09:32 CST 2022
     */
    int insertSelective(TransactionRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction_remark
     *
     * @mbggenerated Fri Sep 16 17:09:32 CST 2022
     */
    TransactionRemark selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction_remark
     *
     * @mbggenerated Fri Sep 16 17:09:32 CST 2022
     */
    int updateByPrimaryKeySelective(TransactionRemark record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_transaction_remark
     *
     * @mbggenerated Fri Sep 16 17:09:32 CST 2022
     */
    int updateByPrimaryKey(TransactionRemark record);

    /**
     * 保存线索下所有的备注转换到交易备注表中
     */
    int insertTransactionRemark(List<TransactionRemark> transactionRemarks);
    /**
     * 根据交易Id查询交易备注
     */
    List<TransactionRemark> selectTransactionRemarkByTransactionId(@Param("transactionId") String transactionId);
}