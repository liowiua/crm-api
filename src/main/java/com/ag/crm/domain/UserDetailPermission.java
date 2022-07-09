package com.ag.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//用户权限信息
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailPermission {
    private Integer id;
    private String userName;
    private List<Module> modules;
    private List<String> roles;

}
