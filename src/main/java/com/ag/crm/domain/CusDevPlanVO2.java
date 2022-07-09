package com.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class CusDevPlanVO2 {

        private Integer id;

        private String chanceSource;

        private String customerName;

        private Integer cgjl;

        private String overview;

        private String linkMan;

        private String linkPhone;

        private String description;

        private String createMan;

        private String assignMan;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date assignTime;

        private Integer state;

        private Integer devResult;

        private Integer isValid;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createDate;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date updateDate;

        // 分配人
        private String uname;





}
