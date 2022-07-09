package com.ag.crm.controller;

import com.ag.crm.domain.CustomerLinkman;
import com.ag.crm.domain.ResultVO;
import com.ag.crm.service.CustomerLinkmanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("customerLinkman")
@RestController
@Api(value = "提供客户联系人表的CRUD接口",tags = "客户联系人管理")
public class CustomerLinkmanController {

    @Autowired
    private CustomerLinkmanService customerLinkmanService;

    @GetMapping("selectByCId")
    @ApiOperation("根据客户id查询联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "cId", value = "客户id",required = true),
    })
    public ResultVO selectByCId(Integer cId){
        return customerLinkmanService.selectByCId(cId);
    }

    @PostMapping("insertLinkman")
    @ApiOperation("添加联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "cusId", value = "客户id",required = true),
            @ApiImplicitParam(dataType = "string",name = "linkName", value = "联系人姓名",required = true),
            @ApiImplicitParam(dataType = "string",name = "sex", value = "性别",required = true),
            @ApiImplicitParam(dataType = "string",name = "zhiwei", value = "职位",required = true),
            @ApiImplicitParam(dataType = "string",name = "officePhone", value = "办公电话",required = true),
            @ApiImplicitParam(dataType = "string",name = "phone", value = "手机号",required = true),
    })
    public ResultVO insertLinkman(@RequestBody CustomerLinkman customerLinkman){
        return customerLinkmanService.insertLinkman(customerLinkman);
    }

    @PostMapping("updateLinkman")
    @ApiOperation("修改字典数据")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "id",required = true),
            @ApiImplicitParam(dataType = "int",name = "cusId", value = "客户id",required = true),
            @ApiImplicitParam(dataType = "string",name = "linkName", value = "联系人姓名",required = true),
            @ApiImplicitParam(dataType = "string",name = "sex", value = "性别",required = true),
            @ApiImplicitParam(dataType = "string",name = "zhiwei", value = "职位",required = true),
            @ApiImplicitParam(dataType = "string",name = "officePhone", value = "办公电话",required = true),
            @ApiImplicitParam(dataType = "string",name = "phone", value = "手机号",required = true),
    })
    public ResultVO updateLinkman(@RequestBody CustomerLinkman customerLinkman){
        return customerLinkmanService.updateLinkman(customerLinkman);
    }

    @PostMapping("deleteLinkmanById")
    @ApiOperation("删除联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "int",name = "id", value = "id",required = true),
    })
    public ResultVO deleteLinkmanById(Integer id){
        return customerLinkmanService.deleteLinkmanById(id);
    }

    @PostMapping("deleteLinkmanByIds")
    @ApiOperation("批量删除联系人")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "string",name = "ids", value = "id,多个id以','分隔",required = true),
    })
    public ResultVO deleteLinkmanById(String ids){
        return customerLinkmanService.deleteLinkmanByIds(ids);
    }



}
