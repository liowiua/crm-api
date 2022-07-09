package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CustomerServeVO {

    private Integer id;

    private Integer serveType;

    private String overview;

    private String customer;

    private String state;

    private String serviceRequest;

    private String createPeople;

    private String assigner;

    private Date assignTime;

    private String serviceProce;

    private String serviceProcePeople;

    private Date serviceProceTime;

    private String serviceProceResult;

    private Integer myd;

    private Integer isValid;

    private Date updateDate;

    private Date createDate;

    private String dicValue; // 服务类型

}
