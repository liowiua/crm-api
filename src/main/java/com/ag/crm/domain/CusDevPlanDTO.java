package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CusDevPlanDTO {

    private String customerName;
    private String createMan;
    private String devResult;

    Integer pageCount;
    Integer pageSize;

}
