package com.ag.crm.dao;

import com.ag.crm.domain.Module;
import com.ag.crm.domain.Permission;
import com.ag.crm.domain.Role;

import java.util.List;
import java.util.Map;

public interface RoleMapper {

    int insert(Role record);

    int updateById(Role record);

    int deleteById(Integer id);


    int deletePermissionById(Integer id);

    List<Role> selectAll();

    /** 插入多条权限 */
    int insertPermissions(List<Permission> list);

    /** 单用户角色 */
    List<Role> selectByUserId(Integer id);

    /** 通过用户id获取用户角色表id */
    List<Integer> selectUserRoleIdByUserId( Integer id );

    List<String> selectRoleNamesByUserId(Integer id);

    /** 单用户插入角色*/
    int insertUserAndRoles(List list);

    /** 批量删除 */
    int deleteUserRoleByIds(List<Integer> list);


    Map<Integer, String> selectUserIdAndNameByRoleName(String roleName);
}
