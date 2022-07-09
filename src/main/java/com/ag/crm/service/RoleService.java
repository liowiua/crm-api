package com.ag.crm.service;

import com.ag.crm.domain.Role;
import com.ag.crm.domain.RolePerm;
import com.ag.crm.domain.UpdateUserRoles;
import com.ag.crm.domain.ResultVO;
import org.springframework.web.bind.annotation.RequestBody;

public interface RoleService {

    ResultVO createRole(Role role);

    ResultVO updateRole(Role role);

    ResultVO delete(Integer id);

    ResultVO listAll();

    ResultVO findAllPermission(Integer id);

    ResultVO authenticate(RolePerm rolePerm);

        /** 插入记录到用户角色表 */
    ResultVO insertUserRoles(UpdateUserRoles updateUserRoles);

    /** 删除用户角色表记录 */
    ResultVO deleteUserRoles(UpdateUserRoles updateUserRoles);

//    int create(Module module);

//    int update(Integer id, Module module);

}