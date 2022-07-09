package com.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;


@Data
@ToString
public class Customer {

    private Integer id; // 主键ID

    private String khno; // 客户编号

    private String name; // 客户名称

    private String area;

    private Integer cusManagerId;

    private Integer level; // 客户级别

    private Integer myd;

    private Integer xyd;

    private String address;

    private String phone; // 手机号码

    private String webSite;

    private String yyzzzch;

    private String fr; // 法人代表

    private String zczj;

    private String nyye;

    private String khyh;

    private String khzh;

    private String dsdjh;

    private String gsdjh;

    private Integer state; // 流失状态  0=正常 1=流失

    private Integer isValid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;

}
