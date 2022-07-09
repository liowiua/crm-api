package com.ag.crm.controller;

import com.ag.crm.domain.CustomerLossDTO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerLossService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "提供客户流失表的CRUD接口",tags = "客户流失管理")

@RequestMapping("customerLoss")
public class CustomerLossController {


    @Autowired
    private CustomerLossService customerLossService;

    @GetMapping("customerLossAnalysis")
    @ApiOperation("客户流失分析接口")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO customerLossAnalysis(Integer pageCount,Integer pageSize){
        return customerLossService.customerLossAnalysis(pageCount,pageSize);
    }

    @PostMapping("selectByParams")
    @ApiOperation("客户流失管理查询接口")
    @ApiImplicitParams({

            @ApiImplicitParam(dataType = "string",name = "customerName", value = "客户名称",required = true),
            @ApiImplicitParam(dataType = "int",name = "state", value = "流失状态  0=暂缓流失状态  1=确认流失状态",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectByParams(@RequestBody CustomerLossDTO customerLossDTO){
        return customerLossService.selectByParams(customerLossDTO);
    }


    @GetMapping("selectById")
    @ApiOperation("根据流失表id查询对应客户流失记录")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "lossId", value = "流失id",required = true)
    })
    public ResultVO selectById(Integer lossId){
        return customerLossService.selectById(lossId);
    }

    @GetMapping("updateCustomerLossStateById")
    @ApiOperation("确认客户流失")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "lossId", value = "流失id",required = true),
            @ApiImplicitParam(dataType = "string",name = "lossReason", value = "流失原因",required = true)
    })
    public ResultVO updateCustomerLossStateById(Integer lossId,String lossReason){
        return customerLossService.updateCustomerLossStateById(lossId,lossReason);
    }

}
