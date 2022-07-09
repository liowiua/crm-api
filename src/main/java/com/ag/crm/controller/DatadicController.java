package com.ag.crm.controller;

import com.ag.crm.domain.Datadic;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.DatadicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("datadic")
@Api(value = "提供字典表的CRUD接口",tags = "字典管理")
public class DatadicController {

    @Autowired
    private DatadicService datadicService;

    @GetMapping("selectAll")
    @ApiOperation("分页查询全部字典信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectAll(Integer pageCount,Integer pageSize){
        return datadicService.selectAll(pageCount,pageSize);
    }

    @PostMapping("insertDatadic")
    @ApiOperation("添加字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "dataDicName", value = "字典名",required = true),
            @ApiImplicitParam(dataType = "string",name = "dataDicValue", value = "字典值",required = true),
    })
    public ResultVO insertDatadic(@RequestBody Datadic datadic){
        return datadicService.insertDatadic(datadic);
    }

    @PostMapping("updateDatadic")
    @ApiOperation("修改字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "字典id",required = true),
            @ApiImplicitParam(dataType = "string",name = "dataDicName", value = "字典名",required = true),
            @ApiImplicitParam(dataType = "string",name = "dataDicValue", value = "字典值",required = true),
    })
    public ResultVO updateDatadic(@RequestBody Datadic datadic){
        return datadicService.updateDatadic(datadic);
    }

    @PostMapping("deleteDatadicById")
    @ApiOperation("删除字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "字典id",required = true),
    })
    public ResultVO deleteDatadicById(Integer id){
        return datadicService.deleteDatadicById(id);
    }

    @PostMapping("deleteDatadicByIds")
    @ApiOperation("批量删除字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "ids", value = "字典id,以','分隔",required = true),
    })
    public ResultVO deleteDatadicByIds(String ids){
        return datadicService.deleteDatadicByIds(ids);
    }

}
