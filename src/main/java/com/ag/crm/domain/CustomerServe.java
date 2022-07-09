package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CustomerServe {

    private Integer id;

    private Integer serveType;

    private String overview; //概要

    private Integer customer;

    private String state;

    private String serviceRequest;

    private Integer createPeople;

    private Integer assigner;

    private Date assignTime;

    private String serviceProce;

    private Integer serviceProcePeople;

    private Date serviceProceTime;

    private String serviceProceResult;

    private Integer myd;

    private Integer isValid;

    private Date updateDate;

    private Date createDate;
    private String dicValue; // 服务类型
}
