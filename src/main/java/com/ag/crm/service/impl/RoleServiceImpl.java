package com.ag.crm.service.impl;

import com.ag.crm.dao.ModuleMapper;
import com.ag.crm.domain.*;
import com.ag.crm.dao.RoleMapper;
import com.ag.crm.service.RoleService;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    protected ModuleMapper moduleMapper;

    @Override
    public ResultVO createRole(Role role){
        if(roleMapper.insert(role) > 0)
            return new ResultVO(200,"操作成功",null);
        else
            return new ResultVO(500,"系统异常",null);
    }

    @Override
    public ResultVO updateRole(Role role){
        if(roleMapper.updateById(role) > 0)
            return new ResultVO(200,"操作成功",null);
        else
            return new ResultVO(500,"系统异常",null);
    }

    @Override
    public ResultVO delete(Integer id){
        if(roleMapper.deleteById(id) > 0)
            return new ResultVO(200,"操作成功",null);
        else
            return new ResultVO(500,"系统异常",null);
    }

    @Override
    public ResultVO listAll(){
        List<Role> list = roleMapper.selectAll();
        DataList roleList = new DataList(list);
        if(list != null)
            return new ResultVO(200,"操作成功",roleList);
        else
            return new ResultVO(500,"系统异常",null);
    }

    @Override
    public ResultVO findAllPermission(Integer roleid){
        List<Module> list = moduleMapper.selectAllPermissionByRoleId(roleid);
        DataList dataList = new DataList(list);
        if(list != null)
            return new ResultVO(200,"操作成功",dataList);
        else
            return new ResultVO(500,"系统异常",null);
    }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public ResultVO authenticate(RolePerm rolePerm){

        int deleteResult = roleMapper.deletePermissionById(rolePerm.getId());

        if(rolePerm.getModules().size() == 0)
            return new ResultVO(200,"操作成功",null);

        List<Permission> list = rolePerm.getModules().stream()
                .map(i->new Permission( rolePerm.getId(),i))
                .collect(Collectors.toList());

        if(list != null){
            roleMapper.insertPermissions(list);
            return new ResultVO(200,"操作成功",list);
        }else
            return new ResultVO(500,"系统异常",null);
    }


//    public ResultVO selectAllUserAndRoles(){
//        return new ResultVO(200,"查询成功",userMapper.selectUsersRoles());
//    }

    public ResultVO insertUserRoles(UpdateUserRoles updateUserRoles){
        List<RelationTable> list = new ArrayList<>();
        for(Integer i : updateUserRoles.getList()){
            list.add(new RelationTable(updateUserRoles.getId(), i));
        }
        if(list.isEmpty())
            return new ResultVO(500,"操作失败",0);
        if (roleMapper.insertUserAndRoles(list) >= 0)
            return new ResultVO(200,"操作成功", 1);
        else
            return new ResultVO(500,"操作失败",0);
    }

    public ResultVO deleteUserRoles(UpdateUserRoles updateUserRoles){
        List<Integer>  targetId = roleMapper.selectUserRoleIdByUserId(updateUserRoles.getId());
        int result = roleMapper.deleteUserRoleByIds(targetId);
        if (result > 0)
            return new ResultVO(200,"操作成功", 1);
        else
            return new ResultVO(500,"操作失败", 0);
    }

}
