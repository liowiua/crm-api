package com.ag.crm.domain;

import lombok.Data;

import java.util.List;

@Data
public class RolePerm {
    private Integer id;
    private List<Integer> modules;
}
