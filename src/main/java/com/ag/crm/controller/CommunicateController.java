package com.ag.crm.controller;


import com.ag.crm.domain.CustomerContact;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CommunicateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("communicate")
@Api(value = "提供客户交往记录的CRUD接口",tags = "交往记录管理")
public class CommunicateController {

    @Autowired
    private CommunicateService communicateService;

    @GetMapping("selectByCId")
    @ApiOperation("根据客户id分页查询交往记录")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "cId", value = "客户id",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectByCId(Integer cId,Integer pageCount,Integer pageSize){
        return communicateService.selectByCId(cId,pageCount,pageSize);
    }

    @PostMapping("insertCommunication")
    @ApiOperation("新增交往记录")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "cusId", value = "客户id",required = true),
            @ApiImplicitParam(dataType = "date",name = "contactTime", value = "交往时间",required = true),
            @ApiImplicitParam(dataType = "string",name = "address", value = "地址",required = true),
            @ApiImplicitParam(dataType = "string",name = "overview", value = "交往内容",required = true)
    })
    public ResultVO insertCommunication(@RequestBody CustomerContact customerContact){
        return  communicateService.insertCommunication(customerContact);
    }


}
