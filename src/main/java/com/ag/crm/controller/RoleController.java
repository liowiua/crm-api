package com.ag.crm.controller;

import com.ag.crm.domain.ResultVO;
import com.ag.crm.domain.Role;
import com.ag.crm.domain.RolePerm;
import com.ag.crm.service.RoleService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "",tags = "角色管理")
@PreAuthorize("hasAuthority('sys:set:role')")
@RequestMapping("admin/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ApiOperation("增加角色")
    @PostMapping("create")
    public ResultVO delete(@RequestBody Role role){
        return roleService.createRole(role);
    }

    @ApiOperation("修改角色")
    @PostMapping("update")
    public ResultVO update(@RequestBody Role role){
        return roleService.updateRole(role);
    }

    @ApiOperation("删除角色")
    @PostMapping("delete")
    public ResultVO delete(Integer id){
        System.out.println(id);
        return roleService.delete(id);
    }

    @ApiOperation("查询所有角色")
    @GetMapping("list")
    public ResultVO list() {
        return roleService.listAll();
    }

    @ApiOperation("查询角色拥有的所有权限")
    @GetMapping("queryAllPermission")
    public ResultVO queryAllPermission(Integer roleid) {
        return roleService.findAllPermission(roleid);
    }

    @ApiOperation("为角色分配权限")
    @PostMapping("authenticate")
    public ResultVO authenticate(@RequestBody RolePerm rolePerm) {
        System.out.println(rolePerm);
        return roleService.authenticate(rolePerm);
    }
}