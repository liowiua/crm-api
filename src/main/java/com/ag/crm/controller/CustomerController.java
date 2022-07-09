package com.ag.crm.controller;

import com.ag.crm.domain.CustomerDTO;
import com.ag.crm.domain.CustomerVO;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "提供客户的CRUD接口",tags = "客户管理")
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation("新增客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "customerId", value = "客户id",required = false),
            @ApiImplicitParam(dataType = "string",name = "name", value = "客户名字",required = true),
            @ApiImplicitParam(dataType = "string",name = "contacts", value = "首要联系人",required = true),
            @ApiImplicitParam(dataType = "string",name = "tel", value = "联系人电话",required = true),
            @ApiImplicitParam(dataType = "string",name = "userId", value = "客户经理id",required = true),
            @ApiImplicitParam(dataType = "string",name = "custcategoryId", value = "客户分类id",required = true),
            @ApiImplicitParam(dataType = "string",name = "creditId", value = "客户可信度",required = true),
            @ApiImplicitParam(dataType = "string",name = "satisfiedId", value = "客户满意度",required = true),
            @ApiImplicitParam(dataType = "string",name = "address", value = "公司地址",required = true),
            @ApiImplicitParam(dataType = "string",name = "url", value = "公司网址",required = true),
            @ApiImplicitParam(dataType = "string",name = "legalPerson", value = "法人代表",required = true),
            @ApiImplicitParam(dataType = "string",name = "license", value = "执照号码",required = false),
            @ApiImplicitParam(dataType = "string",name = "fund", value = "公司注册金",required = false),
            @ApiImplicitParam(dataType = "string",name = "turnover", value = "营业额",required = false),
            @ApiImplicitParam(dataType = "string",name = "bank", value = "银行",required = true),
            @ApiImplicitParam(dataType = "string",name = "bankNumber", value = "银行账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "stateTax", value = "国税登记号",required = false),
            @ApiImplicitParam(dataType = "string",name = "landTax", value = "地税登记号",required = false),
            @ApiImplicitParam(dataType = "string",name = "custfromId", value = "客户来源",required = true),
            @ApiImplicitParam(dataType = "string",name = "changes", value = "是否转移",required = true)
    })
    @PostMapping("insert")
    public ResultVO insert(@RequestBody CustomerVO customer){
        System.out.println(customer);
        return customerService.insert(customer);
    }


    @ApiOperation("修改客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "customerId", value = "客户id",required = false),
            @ApiImplicitParam(dataType = "string",name = "name", value = "客户名字",required = true),
            @ApiImplicitParam(dataType = "string",name = "contacts", value = "首要联系人",required = true),
            @ApiImplicitParam(dataType = "string",name = "tel", value = "联系人电话",required = true),
            @ApiImplicitParam(dataType = "int",name = "userId", value = "客户经理id",required = true),
            @ApiImplicitParam(dataType = "int",name = "custcategoryId", value = "客户分类id",required = true),
            @ApiImplicitParam(dataType = "int",name = "creditId", value = "客户可信度",required = true),
            @ApiImplicitParam(dataType = "int",name = "satisfiedId", value = "客户满意度",required = true),
            @ApiImplicitParam(dataType = "string",name = "address", value = "公司地址",required = true),
            @ApiImplicitParam(dataType = "string",name = "url", value = "公司网址",required = true),
            @ApiImplicitParam(dataType = "string",name = "legalPerson", value = "法人代表",required = true),
            @ApiImplicitParam(dataType = "string",name = "license", value = "执照号码",required = false),
            @ApiImplicitParam(dataType = "string",name = "fund", value = "公司注册金",required = false),
            @ApiImplicitParam(dataType = "string",name = "turnover", value = "营业额",required = false),
            @ApiImplicitParam(dataType = "string",name = "bank", value = "银行",required = true),
            @ApiImplicitParam(dataType = "int",name = "bankNumber", value = "银行账号",required = true),
            @ApiImplicitParam(dataType = "string",name = "stateTax", value = "国税登记号",required = false),
            @ApiImplicitParam(dataType = "string",name = "landTax", value = "地税登记号",required = false),
            @ApiImplicitParam(dataType = "int",name = "custfromId", value = "客户来源",required = true),
            @ApiImplicitParam(dataType = "string",name = "changes", value = "是否转移",required = true)
    })
    @PostMapping("edit")
    public ResultVO edit(@RequestBody CustomerVO customer){
        return customerService.edit(customer);
    }

    @ApiOperation("删除客户信息")
    @ApiImplicitParams({

            @ApiImplicitParam(dataType = "int",name = "customerId", value = "客户id",required = true)
    })
    @PostMapping("delete")
    public ResultVO delete(Integer customerId){
        System.out.println(customerId);
        return customerService.delete(customerId);
    }

    @ApiOperation("根据条件分页查询客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "name", value = "客户名",required = true),
            @ApiImplicitParam(dataType = "string",name = "level", value = "客户级别",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    @PostMapping("selectByParams")
    public ResultVO selectByParams(@RequestBody CustomerVO customerVO){
        return customerService.selectByParams(customerVO,customerVO.getPageCount(),customerVO.getPageSize());
    }

    @GetMapping("selectById")
    @ApiOperation("根据客户id查询客户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "客户id",required = true)
    })
    public ResultVO selectById(Integer id){
        return customerService.selectById(id);
    }

    @ApiOperation("根据条件分页查询客户贡献信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "customerName", value = "客户名",required = true),
            @ApiImplicitParam(dataType = "date",name = "time", value = "客户编码",required = true),
            @ApiImplicitParam(dataType = "int",name = "type", value = "客户级别",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageCount", value = "页码",required = true),
            @ApiImplicitParam(dataType = "int",name = "pageSize", value = "每页条数",required = true)
    })
    @PostMapping("selectCustomerContributionByParams")
    public ResultVO selectCustomerContributionByParams(CustomerDTO customerDTO){
        return customerService.selectCustomerContributionByParams(customerDTO);
    }

    @ApiOperation("折线图数据处理接口")
    @GetMapping("countCustomerMake")
    public ResultVO countCustomerMake(){
        return customerService.countCustomerMake();
    }

    @ApiOperation("饼状图数据处理接口")
    @GetMapping("countCustomerMake02")
    public ResultVO countCustomerMake02(){
        return customerService.countCustomerMake02();
    }

}
