package com.ag.crm.controller;

import com.ag.crm.domain.CusDevPlan;
import com.ag.crm.domain.CusDevPlanDTO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CusDevPlanService;
import com.ag.crm.service.SaleChanceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "提供对客户开发计划操作接口",tags = "客户开发计划")
@RequestMapping("cusDevPlan")
public class CusDevPlanController {

    @Autowired
    private CusDevPlanService cusDevPlanService;
    @Autowired
    private SaleChanceService saleChanceService;

    @PostMapping("selectByParams")
    @ApiOperation("多条件查询客户开发计划")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "customerName", value = "客户名",required = false),
            @ApiImplicitParam(dataType = "string",name = "createMan", value = "创建人",required = false),
            @ApiImplicitParam(dataType = "string",name = "devResult", value = "开发状态",required = false),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectByParams(@RequestBody CusDevPlanDTO cusDevPlanDTO){
        return cusDevPlanService.selectByParams(cusDevPlanDTO,cusDevPlanDTO.getPageCount(),cusDevPlanDTO.getPageSize());
    }


    @GetMapping("selectBySaleChanceId")
    @ApiOperation("根据营销机会id查询客户开发计划")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "营销机会id",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)

    })
    public ResultVO selectBySaleChanceId(Integer id,Integer pageCount,Integer pageSize){
        return cusDevPlanService.selectBySaleChanceId(id,pageCount,pageSize);
    }

    @GetMapping("selectById")
    @ApiOperation("根据开发计划id查询客户开发计划")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "开发计划id",required = true),

    })
    public ResultVO selectById(Integer id){
        return cusDevPlanService.selectById(id);
    }

    @ApiOperation("添加客户开发计划")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "saleChanceId", value = "营销机会id",required = true),
            @ApiImplicitParam(dataType = "string",name = "planItem", value = "计划内容",required = true),
            @ApiImplicitParam(dataType = "date",name = "planDate", value = "计划时间",required = true),
            @ApiImplicitParam(dataType = "string",name = "exeAffect", value = "执行效果",required = true)

    })
    @PostMapping("insertCusDevPlan")
    public ResultVO insertCusDevPlan(@RequestBody CusDevPlan cusDevPlan){
        System.out.println(cusDevPlan);
        return cusDevPlanService.insertCusDevPlan(cusDevPlan);
    }

    @ApiOperation("更新客户开发计划")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "开发计划id",required = true),
            @ApiImplicitParam(dataType = "string",name = "planItem", value = "计划内容",required = true),
            @ApiImplicitParam(dataType = "date",name = "planDate", value = "计划时间",required = true),
            @ApiImplicitParam(dataType = "string",name = "exeAffect", value = "执行效果",required = true)

    })
    @PostMapping("updateCusDevPlan")
    public ResultVO updateCusDevPlan(@RequestBody CusDevPlan cusDevPlan){
        return cusDevPlanService.updateCusDevPlan(cusDevPlan);
    }

    @GetMapping("deleteById")
    @ApiOperation("根据开发计划id删除客户开发计划")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "开发计划id",required = true),

    })
    public ResultVO deleteById(Integer id){
        return cusDevPlanService.delectById(id);
    }

}
