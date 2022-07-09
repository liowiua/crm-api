package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaleChanceDTO {

    private String customerName;
    private String createMan;
    private String state;

    private Integer pageCount;
    private Integer pageSize;

}
