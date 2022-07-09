package com.ag.crm.service.impl;

import com.ag.crm.domain.DataList;
import com.ag.crm.domain.Module;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.dao.ModuleMapper;
import com.ag.crm.service.ModuleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ModuleServiceImpl implements ModuleService {

    @Autowired
    private ModuleMapper moduleMapper;

    @Override
    public ResultVO selectAll( int pageNum, int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Module> Modules = moduleMapper.selectAll();
        PageInfo<Module> pageInfo = new PageInfo<>(Modules);
        DataList dataList = new DataList(pageInfo.getList());
        return new ResultVO(200,"查询成功",dataList);
    }

    @Override
    public ResultVO hideModule(Integer id, Boolean hidden){

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("hidden", hidden);

        if(moduleMapper.hideModule(map) > 0)
            return new ResultVO(200,"操作成功",null);
        else
            return new ResultVO(500,"系统异常",null);
    }
//
//    @Override
//    public ResultVO info(String  token) {
//
//        Map<String, Object> map = JWTUtils.checkToken(token);
//        String userName = (String) map.get("userName");
//
//        UserDetailPermission moduleAndRole = moduleMapper.selectUserAll();
//
//        return ResultVO.success("查询成功",moduleAndRole);
//    }
}
