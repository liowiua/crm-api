package com.ag.crm.dao;

import com.ag.crm.domain.CusDevPlan;
import com.ag.crm.domain.CusDevPlanDTO;
import com.ag.crm.domain.CusDevPlanVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CusDevPlanMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cus_dev_plan
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cus_dev_plan
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    int insert(CusDevPlan record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cus_dev_plan
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    CusDevPlan selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cus_dev_plan
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    List<CusDevPlan> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_cus_dev_plan
     *
     * @mbg.generated Wed Jun 15 17:10:54 CST 2022
     */
    int updateByPrimaryKey(CusDevPlan record);

    List<CusDevPlanVO> selectByParams(@Param("cusDevPlanDTO") CusDevPlanDTO cusDevPlanDTO,
                                      @Param("start") int start,
                                      @Param("pageSize") Integer pageSize);

    int selectCount();

    List<CusDevPlan> selectBySaleChanceId(@Param("id") Integer id,@Param("start") int start,@Param("pageSize") Integer pageSize);

    int selectCountBySaleChanceId(Integer id);

    int updateCusDevPlanById(CusDevPlan cusDevPlan);
}
