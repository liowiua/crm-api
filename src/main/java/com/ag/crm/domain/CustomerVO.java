package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CustomerVO {

    private Integer id;

    private String khno;

    private String name;

    private String area;

    private String cusManagerId;

    private String level;

    private Integer myd;

    private Integer xyd;

    private String address;

    private String phone;

    private String webSite;

    private String yyzzzch;

    private String fr;

    private String zczj;

    private String nyye;

    private String khyh;

    private String khzh;

    private String dsdjh;

    private String gsdjh;

    private Integer state;

    private Integer isValid;

    private Date createDate;

    private Date updateDate;

    private Integer pageCount;
    private Integer pageSize;

}
