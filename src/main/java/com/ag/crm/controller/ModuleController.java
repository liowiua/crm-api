package com.ag.crm.controller;

import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.ModuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "",tags = "模块管理")
@PreAuthorize("hasAuthority('sys:set:mod')")
@RequestMapping("admin/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;

    @ApiOperation("分页查询所有模块")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int", name = "pageNum", value = "页数", required = true),
            @ApiImplicitParam(dataType = "int", name = "pageSize", value = "页大小", required = true)
    })
    @GetMapping("list")
    public ResultVO list(int pageNum, int pageSize) {
        return moduleService.selectAll(pageNum, pageSize);
    }

    @ApiOperation("隐藏模块")
    @GetMapping("hide")
    public ResultVO hideModule(Integer id, boolean hidden) {
        System.out.println(hidden +"-" + id);
        return moduleService.hideModule(id, hidden);
    }

}