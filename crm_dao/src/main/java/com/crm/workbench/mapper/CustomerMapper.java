package com.crm.workbench.mapper;

import com.crm.workbench.entity.Customer;
import com.crm.workbench.entity.Transaction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu Sep 15 19:27:19 CST 2022
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu Sep 15 19:27:19 CST 2022
     */
    int insert(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu Sep 15 19:27:19 CST 2022
     */
    int insertSelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu Sep 15 19:27:19 CST 2022
     */
    Customer selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu Sep 15 19:27:19 CST 2022
     */
    int updateByPrimaryKeySelective(Customer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tbl_customer
     *
     * @mbggenerated Thu Sep 15 19:27:19 CST 2022
     */
    int updateByPrimaryKey(Customer record);

    /**
     * 将线索有关公司的信息转换到客户表中
     */
    int insertCustomer(@Param("customer") Customer customer);

    /**
     * 根据id查询客户
     */
    Customer selectCustomerByCustomerId(@Param("customerId") String customerId);
    /**
     * 根据客户名称模糊查询所有客户名称
     */
    List<String> selectAllCustomerNameByName(String name);
    /**
     * 根据客户名称查询客户对象
     */
    Customer selectCustomerByCustomerName(String customerName);
}