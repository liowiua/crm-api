package com.ag.crm.domain;

import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRoles {

    /** 用户id*/
    private Integer id;
    /** 操作  true增加 false删除*/
    private boolean insOrDel;
    /** 角色id列表*/
    private List<Integer> list;

    @Override
    public String toString(){
        String s = "";
        s += Integer.toString(id);
        s += "--";
        for(Integer i : list){
          s += i.toString();
        }
        return s;
    }
}
