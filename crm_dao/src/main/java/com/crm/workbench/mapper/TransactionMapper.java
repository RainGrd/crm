package com.crm.workbench.mapper;

import com.crm.common.Vo.FunnelVO;
import com.crm.workbench.entity.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据分页条件查询数据
     */
    List<Transaction> selectTransactionListByConditionForPage(@Param("map") Map<String, String> map);
    /**
     * 根据分页条件查询相同数目
     */
    int selectCountByCondition(@Param("map") Map<String, String> map);
    /**
     * 根据交易id查询交易对象
     */
    Transaction selectTransactionByTransactionId(@Param("transactionId") String transactionId);
    /**
     * 查询交易表中各个阶段的数据量
     */
    List<FunnelVO> selectCountOfTransactionGroupByStage();
}