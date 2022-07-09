package com.ag.crm.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer id;
    private String roleName;
    private String roleRemark;
    private Date createDate;
    private Date updateDate;
    private Boolean isValid;
}