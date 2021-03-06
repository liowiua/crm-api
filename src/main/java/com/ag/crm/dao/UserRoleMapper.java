package com.ag.crm.dao;

import com.ag.crm.domain.UserRole;
import java.util.List;

public interface UserRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Wed Jun 22 08:44:35 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Wed Jun 22 08:44:35 CST 2022
     */
    int insert(UserRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Wed Jun 22 08:44:35 CST 2022
     */
    UserRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Wed Jun 22 08:44:35 CST 2022
     */
    List<UserRole> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user_role
     *
     * @mbg.generated Wed Jun 22 08:44:35 CST 2022
     */
    int updateByPrimaryKey(UserRole record);

    List<Integer> selectUserIdByRoleId(int roleId);
}
