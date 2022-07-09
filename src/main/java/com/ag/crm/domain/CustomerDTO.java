package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerDTO {

    private String customerName; // 客户名称
    private String customerNo; // 客户编号
    private String level; // 客户级别

    private String time; // 订单时间
    private Integer type; // 金额区间  1=1-1000 2=1000-3000  3=3000-5000  4=5000以上

    private Integer pageCount;
    private Integer pageSize;
}
