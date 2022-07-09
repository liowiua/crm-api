package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Datadic {
    private Integer id;

    private String dataDicName;

    private String dataDicValue;

    private Integer isValid;

    private Date createDate;

    private Date updateDate;

}
