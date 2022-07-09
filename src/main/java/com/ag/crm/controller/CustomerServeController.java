package com.ag.crm.controller;

import com.ag.crm.domain.CustomerServe;
import com.ag.crm.domain.CustomerServeDTO;
import com.ag.crm.domain.CustomerServeVO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerServeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customerServe")
@Api(value = "提供客户服务的CRUD接口",tags = "客户服务管理")
public class CustomerServeController {

    @Autowired
    private CustomerServeService customerServeService;

    @PostMapping("selectByParams")
    @ApiOperation("客户服务查询")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "customer", value = "客户名称",required = true),
            @ApiImplicitParam(dataType = "int",name = "serveType", value = "服务类型",required = true),
            @ApiImplicitParam(dataType = "string",name = "state", value = "服务状态  服务创建=fw_001  服务分配=fw_002  服务处理=fw_003  服务反馈=fw_004  服务归档=fw_005",required = true),
            @ApiImplicitParam(dataType = "int",name = "assigner", value = "分配人",required = true),
            @ApiImplicitParam(dataType = "int",name = "flag", value = "是否为服务处理页面，是：1，否：不填",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO selectByParams(@RequestBody CustomerServeDTO customerServeDTO){
        return customerServeService.selectByParams(customerServeDTO,customerServeDTO.getFlag());
    }


    @PostMapping("insertCustomerServe")
    @ApiOperation("客户服务创建")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "createPeople", value = "创建人id",required = true),
            @ApiImplicitParam(dataType = "string",name = "customer", value = "客户名称",required = true),
            @ApiImplicitParam(dataType = "int",name = "serveType", value = "服务类型",required = true),
            @ApiImplicitParam(dataType = "string",name = "overview", value = "服务概要",required = true),
            @ApiImplicitParam(dataType = "string",name = "serviceRequest", value = "服务内容",required = true),
    })
    public ResultVO insertCustomerServe(@RequestBody CustomerServeVO customerServeVO){
        return customerServeService.insertCustomerServe(customerServeVO);
    }

    @PostMapping("assignedCustomerServe")
    @ApiOperation("客户服务分配")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "服务表id",required = true),
            @ApiImplicitParam(dataType = "string",name = "assigner", value = "分配人",required = true),

    })
    public ResultVO assignedCustomerServe(@RequestBody CustomerServeVO customerServeVO){
        customerServeVO.setState("fw_002");
        return customerServeService.updateCustomerServe(customerServeVO);
    }

    @PostMapping("procedCustomerServe")
    @ApiOperation("客户服务处理")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "服务表id",required = true),
            @ApiImplicitParam(dataType = "int",name = "serviceProcePeople", value = "处理人id",required = true),

            @ApiImplicitParam(dataType = "string",name = "serviceProce", value = "服务处理内容",required = true),

    })
    public ResultVO procedCustomerServe(@RequestBody CustomerServeVO customerServeVO){
        customerServeVO.setState("fw_003");
        return customerServeService.updateCustomerServe(customerServeVO);
    }

    @PostMapping("feedBackCustomerServe")
    @ApiOperation("客户服务反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "服务表id",required = true),
            @ApiImplicitParam(dataType = "string",name = "serviceProceResult", value = "处理结果",required = true),
            @ApiImplicitParam(dataType = "int",name = "myd", value = "服务反馈满意度",required = true)
    })
    public ResultVO feedBackCustomerServe(@RequestBody CustomerServeVO customerServeVO){
        customerServeVO.setState("fw_004");
        return customerServeService.updateCustomerServe(customerServeVO);
    }

    @PostMapping("archivedCustomerServe")
    @ApiOperation("客户服务归档")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    public ResultVO archivedCustomerServe(Integer pageCount,Integer pageSize){
        return customerServeService.archivedCustomerServe(pageCount,pageSize);
    }

}
