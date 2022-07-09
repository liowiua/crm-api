package com.ag.crm.dao;

import com.ag.crm.domain.Module;
import com.ag.crm.domain.ResultVO;

import java.util.List;
import java.util.Map;

public interface ModuleMapper {

    /** 隐藏模块 */
    int hideModule(Map<String, Object> map);

    List<Module> selectAllPermissionByRoleId(Integer id);

    int deleteByPrimaryKey(Integer id);

    int insert(Module record);

    Module selectByPrimaryKey(Integer id);

    List<Module> selectAll();

    int updateByPrimaryKey(Module record);
}
