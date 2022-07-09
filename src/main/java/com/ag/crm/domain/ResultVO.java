package com.ag.crm.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVO<T> {

    //响应给前端的状态码
    @ApiModelProperty(value = "响应状态码",dataType = "int")
    private int code;

    //响应给前端的提示信息
    @ApiModelProperty("响应提示信息")
    private String msg;

    //响应给前端的数据
    @ApiModelProperty("响应数据")
    private T data;

    public ResultVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static ResultVO success(String msg, Object data){
        return new ResultVO(ResStatus.OK,msg,data);
    }

    public static ResultVO fail(String msg){
        return new ResultVO(ResStatus.NO,msg,null);
    }

    public static ResultVO fail(int code, String msg){
        return new ResultVO(code,msg,null);
    }

}
