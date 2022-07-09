package com.ag.crm.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@Data
public class Log {

    private Integer id;

    private String operationMethod;

    private String url;

    private String operationDesc;

    private String parameter;

    private String ip;


    private Integer timeConsuming;

    private String operationTime;

    private Byte logType;

    private String errorLogMsg;

    private String operationType;

    private String createMan;

}
