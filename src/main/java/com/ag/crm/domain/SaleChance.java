package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class SaleChance {

    private Integer id;

    private String chanceSource;

    private String customerName;

    private Integer cgjl;

    private String overview;

    private String linkMan;

    private String linkPhone;

    private String description;

    private Integer createMan;

    private Integer assignMan;

    private Date assignTime;

    private Integer state;

    private Integer devResult;

    private Integer isValid;

    private Date createDate;

    private Date updateDate;

}
