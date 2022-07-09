package com.ag.crm.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/** 用户角色信息类类 */
@Data
@NoArgsConstructor
public class UsersRoles {
    private Integer id;
    private String userName;
    private String trueName;
    private String email;
    private String phone;
    private Integer isValid;
    private Date createDate;
    private Date updateDate;
    private List<Integer> roles;
}
