package com.ag.crm.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String trueName;
    private String email;
    private String phone;
    private Integer isValid;
    private Date createDate;
    private Date updateDate;
    private String avatar;

}