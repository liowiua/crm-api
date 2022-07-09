package com.ag.crm.controller;

import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.OrderDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orderDetail")
@Api(value = "提供订单详细表的CRUD接口",tags = "订单详细表管理")

public class OrderDetailController {

    @Autowired
    private OrderDetailService orderDetailService;

    @GetMapping("selectByOrderId")
    @ApiOperation("根据订单id查询订单详细")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "orderId", value = "订单id",required = true),
    })
    public ResultVO selectByOrderId(Integer orderId){
        return orderDetailService.selectByOrderId(orderId);
    }

}
