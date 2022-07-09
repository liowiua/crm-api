package com.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ToString
public class CustomerServeVO2 {
    private Integer id;

    private Integer serveType;

    private String overview;

    private Integer customer;

    private String state;

    private String serviceRequest;

    private Integer createPeople;

    private Integer assigner;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 如果传递的参数是Date类型，要求传入的时间字符串的格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date assignTime;

    private String serviceProce;

    private Integer serviceProcePeople;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 如果传递的参数是Date类型，要求传入的时间字符串的格式
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date serviceProceTime;

    private String serviceProceResult;

    private Integer myd;

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;


    private String dicValue; // 服务类型


}
