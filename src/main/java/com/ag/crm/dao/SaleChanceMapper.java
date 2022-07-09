package com.ag.crm.dao;

import com.ag.crm.domain.SaleChance;
import com.ag.crm.domain.SaleChanceDTO;
import com.ag.crm.domain.SaleChanceVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SaleChanceMapper{
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sale_chance
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sale_chance
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    int insert(SaleChance record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sale_chance
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    SaleChance selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sale_chance
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    List<SaleChance> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_sale_chance
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    int updateByPrimaryKey(SaleChance record);

    List<SaleChance> selectAllByPage(Integer start, Integer pageSize);

    Integer selectCount();

    List<SaleChance> selectByCondition(@Param("saleChanceDTO") SaleChanceDTO saleChanceDTO,@Param("userId") int userId, @Param("start") int start);


    int updateDevResult(Integer id, Integer devResult);
}
