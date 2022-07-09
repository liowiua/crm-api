package com.ag.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/** 用于存放一个用户的一个角色 */
@Data
@AllArgsConstructor
public class UserOneRole {
    private Integer userId;

    private Integer roleId;
}
