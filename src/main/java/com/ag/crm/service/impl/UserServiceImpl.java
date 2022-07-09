package com.ag.crm.service.impl;

import com.ag.crm.dao.UserRoleMapper;
import com.ag.crm.domain.*;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.dao.RoleMapper;
import com.ag.crm.dao.UserMapper;
import com.ag.crm.service.UserService;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultVO createUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(userMapper.insertUser(user) > 0)
            return new ResultVO(200, "操作成功",null) ;
        else
            return new ResultVO(500, "系统异常",null) ;
    }

    @Override
    public ResultVO updateUser(User user){
        if(!StringUtils.isEmpty(user.getPassword()))
            user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println(user);
        if(userMapper.updateById(user) > 0)
            return new ResultVO(200, "操作成功",null) ;
        else
            return new ResultVO(500, "系统异常",null) ;
    }


    @Override
    public ResultVO deleteUser(Integer id){
        if(userMapper.deleteById(id) > 0)
            return new ResultVO(200, "删除成功",null) ;
        else
            return new ResultVO(500, "系统异常",null) ;
    }

    public ResultVO deleteUsersById(List<Integer> ids) {
        userMapper.deleteUsersById(ids);
        return new ResultVO(200,"删除成功",null);
    }

    /** 为用户分配角色 */
    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResultVO allocate(UsersRoles usersRoles){
        userMapper.deleteUserAllRolesById(usersRoles.getId());
        List<UserOneRole> list = usersRoles.getRoles().stream()
                .map(i->new UserOneRole(usersRoles.getId(),i)).collect(Collectors.toList());
        if (!list.isEmpty())
            userMapper.insertUsersRoles(list);
        return new ResultVO(200, "操作成功",null);
    }

    @Override
    public ResultVO selectAllManager() {
        List<String> users = userMapper.selectUserNameByRoleName("客户经理");
//        List<Integer> managerId = userRoleMapper.selectUserIdByRoleId(roleId);
//        List<User> users = new ArrayList<>();
//        managerId.forEach(
//                id->{
//                    User user = userMapper.selectAllManager(id);
//                    users.add(user);
//                }
//        );

        return ResultVO.success("查询成功",users);
    }

//    @Override
//    public int uploadImage(String s) {
//        User user = UserThreadLocal.get();
//
//        int i = userMapper.updateAvatar(s,user.getUserName());
//        return i;
//    }

    public ResultVO selectAllUser(int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<User>  users = userMapper.selectAll();
        DataList dataList = new DataList(users);
        return new ResultVO(200,"查询成功",dataList);

    }
//
//    @Override
//    public ResultVO editUser(String file, boolean judge, User user) {
//        if (judge) {
//            user.setAvatar(file);
//            user.setUserPwd(MD5Utils.md5(user.getUserPwd()));
//            int i = userMapper.updateById(user);
//            if (i > 0){
//                return ResultVO.success("修改成功",null);
//            }
//            else{
//                throw new RuntimeException("修改失败");
//            }
//        }
//        return ResultVO.fail("修改失败");
//    }
}
