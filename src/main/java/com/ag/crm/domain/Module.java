package com.ag.crm.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module {
    private Integer id;
    private String name;
    private String title;
    private String url;
    private Integer parentId;
    private String optValue;
    private Integer grade;
    private Boolean hidden;
    private Integer sort;

}
