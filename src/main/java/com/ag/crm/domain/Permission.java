package com.ag.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Permission {
    private Integer roleId;
    private Integer moduleId;
}
