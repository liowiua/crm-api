package com.ag.crm.controller;

import com.ag.crm.domain.CustomerReprieve;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerReprieveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/customerReprieve")
@RestController
@Api(value = "提供客户暂缓流失表的CRUD接口",tags = "客户暂缓流失管理")
public class CustomerReprieveController {


    @Autowired
    private CustomerReprieveService customerReprieveService;

    @GetMapping("selectByLossId")
    @ApiOperation("根据流失id分页查询暂缓措施")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "lossId", value = "流失id",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectByLossId(Integer lossId,Integer pageCount,Integer pageSize){
        return customerReprieveService.selectByLossId(lossId,pageCount,pageSize);
    }


    @PostMapping("insertCustomerReprieve")
    @ApiOperation("添加暂缓数据")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "lossId", value = "流失id",required = true),
            @ApiImplicitParam(dataType = "string",name = "measure", value = "暂缓措施",required = true),
    })
    public ResultVO insertCustomerReprieve(@RequestBody CustomerReprieve customerReprieve){
        return customerReprieveService.insertCustomerReprieve(customerReprieve);
    }

    @PostMapping("updateCustomerReprieve")
    @ApiOperation("修改暂缓数据")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "暂缓id",required = true),
            @ApiImplicitParam(dataType = "int",name = "lossId", value = "流失id",required = true),
            @ApiImplicitParam(dataType = "string",name = "measure", value = "暂缓措施",required = true),
    })
    public ResultVO updateCustomerReprieve(@RequestBody CustomerReprieve customerReprieve){
        return customerReprieveService.updateCustomerReprieve(customerReprieve);
    }

    @GetMapping("selectById")
    @ApiOperation("根据id查询暂缓措施")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "id",required = true),
    })
    public ResultVO selectById(Integer id){
        return customerReprieveService.selectById(id);
    }

    @GetMapping("deleteById")
    @ApiOperation("根据id删除暂缓措施")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "id",required = true),
    })
    public ResultVO deleteById(Integer id){
        return customerReprieveService.deleteById(id);
    }

}
