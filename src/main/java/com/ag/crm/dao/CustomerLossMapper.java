package com.ag.crm.dao;

import com.ag.crm.domain.CustomerLoss;
import com.ag.crm.domain.CustomerLossDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CustomerLossMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer_loss
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer_loss
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    int insert(CustomerLoss record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer_loss
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    CustomerLoss selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer_loss
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    List<CustomerLoss> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_customer_loss
     *
     * @mbg.generated Mon Jun 20 15:55:40 CST 2022
     */
    int updateByPrimaryKey(CustomerLoss record);

    List<CustomerLoss> customerLossAnalysis(@Param("start") Integer start,@Param("pageSize") Integer pageSize);

    int selectCount();


    List<CustomerLoss> selectByParams(CustomerLossDTO customerLossDTO);

    int insertBatch(List<CustomerLoss> customerLossList);

    Integer updateByPrimaryKeySelective(CustomerLoss customerLoss);

    int selectCount1(CustomerLossDTO customerLossDTO);
}
