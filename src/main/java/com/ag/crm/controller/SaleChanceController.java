package com.ag.crm.controller;

import com.ag.crm.domain.SaleChance;
import com.ag.crm.domain.SaleChanceDTO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.domain.SaleChanceVO;
import com.ag.crm.service.SaleChanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "提供对营销机会操作接口",tags = "营销机会管理")
@RequestMapping("saleChance")
public class SaleChanceController {

    @Autowired
    private SaleChanceService saleChanceService;

    @GetMapping("selectAllByPage")
    @ApiOperation("查询全部营销机会")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectAllByPage(Integer pageCount,Integer pageSize){
        return saleChanceService.selectAllByPage(pageCount,pageSize);
    }
    @PostMapping("selectByCondition")
    @ApiOperation("根据条件查询营销机会")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "customerName", value = "客户名",required = false),
            @ApiImplicitParam(dataType = "string",name = "createMan", value = "创建人",required = false),
            @ApiImplicitParam(dataType = "string",name = "state", value = "分配状态,1是已分配，0是未分配",required = false),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectByCondition(@RequestBody SaleChanceDTO saleChanceDTO){
        return saleChanceService.selectByCondition(saleChanceDTO);
    }

    @PostMapping("addSaleChance")
    @ApiOperation("新增营销机会")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "customerName", value = "客户名",required = true),
            @ApiImplicitParam(dataType = "string",name = "chanceSoure", value = "机会来源",required = false),
            @ApiImplicitParam(dataType = "string",name = "linkMan", value = "联系人",required = true),
            @ApiImplicitParam(dataType = "string",name = "linkPhone", value = "联系电话",required = true),
            @ApiImplicitParam(dataType = "string",name = "overview", value = "概要",required = false),
            @ApiImplicitParam(dataType = "int",name = "cgjl", value = "成功几率",required = false),
            @ApiImplicitParam(dataType = "string",name = "description", value = "描述",required = false),
            @ApiImplicitParam(dataType = "int",name = "assignMan", value = "分配人",required = false)
    })
    public ResultVO addSaleChance( @RequestBody  SaleChance saleChance){
        return saleChanceService.addSaleChance(saleChance);
    }

    @PostMapping("delectASaleChance")
    @ApiOperation("删除一个营销机会")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "saleChanceId", value = "营销机会id",required = true)
    })
    public ResultVO delectASaleChance(Integer saleChanceId){
        return saleChanceService.delectASaleChance(saleChanceId);
    }
    @PostMapping("delectMultipleSaleChance")
    @ApiOperation("删除多个营销机会")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "saleChanceIds", value = "营销机会id,以','隔开",required = true)
    })
    public ResultVO delectMultipleSaleChance(String saleChanceIds){
        return saleChanceService.delectMultipleSaleChance(saleChanceIds);
    }


    @PostMapping("editSaleChance")
    @ApiOperation("修改营销机会")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "营销机会id",required = true),
            @ApiImplicitParam(dataType = "string",name = "customerName", value = "客户名",required = true),
            @ApiImplicitParam(dataType = "string",name = "chanceSoure", value = "机会来源",required = false),
            @ApiImplicitParam(dataType = "string",name = "linkMan", value = "联系人",required = true),
            @ApiImplicitParam(dataType = "string",name = "linkPhone", value = "联系电话",required = true),
            @ApiImplicitParam(dataType = "string",name = "overview", value = "概要",required = false),
            @ApiImplicitParam(dataType = "int",name = "cgjl", value = "成功几率",required = false),
            @ApiImplicitParam(dataType = "string",name = "description", value = "描述",required = false),
            @ApiImplicitParam(dataType = "string",name = "assignMan", value = "分配人",required = false)
    })
    public ResultVO editSaleChance(@RequestBody SaleChanceVO saleChance){
        return saleChanceService.editSaleChance(saleChance);
    }


    @GetMapping("updateDevResult")
    @ApiOperation("修改营销机会开发状态")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "营销机会id",required = true),
            @ApiImplicitParam(dataType = "int",name = "devResult", value = "开发结果(0未开发，1开发中，2开发成功，3开发失败)",required = true),

    })
    public ResultVO updateDevResult(Integer id,Integer devResult){
        return saleChanceService.updateDevResult(id,devResult);
    }

}
