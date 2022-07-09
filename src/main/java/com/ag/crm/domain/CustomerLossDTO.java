package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerLossDTO {

    private String customerName; // 客户名称
    private Integer state; // 流失状态  0=暂缓流失状态  1=确认流失状态

    private Integer pageCount;
    private Integer pageSize;

}
