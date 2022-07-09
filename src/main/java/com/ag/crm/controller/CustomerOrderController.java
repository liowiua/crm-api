package com.ag.crm.controller;

import com.ag.crm.domain.CustomerOrderDTO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerOrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customerOrder")
@RestController
@Api(value = "提供客户订单表的CRUD接口",tags = "客户订单管理")
public class CustomerOrderController {

    @Autowired
    private CustomerOrderService customerOrderService;

    @GetMapping("selectById")
    @ApiOperation("根据客户id分页查询所拥有的订单")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "cId", value = "客户id",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectCOrderById(Integer cId,Integer pageCount,Integer pageSize){
        return customerOrderService.selectCOrderById(cId,pageCount,pageSize);
    }

    @PostMapping("insertOrder")
    @ApiOperation("添加订单")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "cusId", value = "客户id",required = true),
            @ApiImplicitParam(dataType = "string",name = "address", value = "收货地址",required = true),
            @ApiImplicitParam(dataType = "string",name = "goodsName", value = "商品名称",required = true),
            @ApiImplicitParam(dataType = "int",name = "goodsNum", value = "购买数量",required = true),
            @ApiImplicitParam(dataType = "string",name = "unit", value = "单位",required = true),
            @ApiImplicitParam(dataType = "int",name = "price", value = "商品单价",required = true),
            @ApiImplicitParam(dataType = "int",name = "sum", value = "价钱总和",required = true)
    })
    public ResultVO insertOrder(@RequestBody CustomerOrderDTO customerOrderDTO){
        return customerOrderService.insertOrder(customerOrderDTO);
    }


}
