package com.ag.crm.service;

import com.ag.crm.domain.User;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.domain.UsersRoles;

import java.util.List;

public interface UserService {

    ResultVO createUser(User user);

    ResultVO updateUser(User user);

    ResultVO deleteUser(Integer id);

    ResultVO deleteUsersById(List<Integer> ids);

    ResultVO selectAllUser(int pageNum, int pageSize);

    ResultVO allocate(UsersRoles usersRoles);

    ResultVO selectAllManager();


//    int uploadImage(String fileSrc);
//
//    ResultVO info(String authorization);
//
//    ResultVO editUser(String file, boolean judge, User user);
}
