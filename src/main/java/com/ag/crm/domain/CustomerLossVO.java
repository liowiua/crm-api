package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CustomerLossVO {

    private Integer id;

    private String cusNo;

    private Date lastOrderTime;

    private Date confirmLossTime;

    private Integer state;

    private String lossReason;

    private Date createDate;

    private Date updateDate;

    private String customerName;

    private String managerName;

}
