package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerOrderDTO {

    private Integer cusId;
    private String address;
    private String goodsName;
    private Integer goodsNum;
    private String unit;
    private Float price;
    private Float sum;

}
