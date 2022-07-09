package com.ag.crm.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomerServeDTO {

    private String customer; // 客户名称
    private Integer serveType; // 服务类型
    private String state; // 服务状态  服务创建=fw_001  服务分配=fw_002  服务处理=fw_003  服务反馈=fw_004  服务归档=fw_005

    private Integer assigner; // 分配人
    private Integer pageCount;
    private Integer pageSize;

    private Integer flag;

}
