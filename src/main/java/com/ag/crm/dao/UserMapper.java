package com.ag.crm.dao;

import com.ag.crm.domain.User;
import com.ag.crm.domain.UserDetailPermission;
import com.ag.crm.domain.UserOneRole;
import com.ag.crm.domain.UsersRoles;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    int insertUser(User user);

    int updateById(User user);

    User selectByUserName(String userName);

    List<String> selectUserNameByRoleName(String userName);

    int deleteById(Integer id);

    int deleteUsersById(List<Integer> ids);

    /** 删除用户所有角色 */
    int deleteUserAllRolesById(Integer id);

    /** 为用户增加多个角色 */
    int insertUsersRoles(List<UserOneRole> list);


    List<User> selectAll();

    /** 查询所有用户的权限*/
    List<UsersRoles> selectUsersRoles();


    String  selectUserNameById(Integer id);

    Integer  selectIdByUserName(String userName);

    int updateAvatar(String s, String userName);

    /** 通过用户id查找允许用户访问的模块 */
//    List<String> selectModuleByUserId(Integer userid);

    /** 通过用户名查询用户所有的权限*/
    UserDetailPermission selectPermissionByUsername(String userName);


}
