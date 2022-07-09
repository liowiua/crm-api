package com.ag.crm.utils;

import com.ag.crm.domain.User;

public class UserThreadLocal {

    private UserThreadLocal(){}
    //线程变量隔离
    private static final ThreadLocal<User> LOCAL = new ThreadLocal<>();

    public static void put(User sysUser){
        LOCAL.set(sysUser);
    }
    public static User get(){
        return LOCAL.get();
    }
    public static void remove(){
        LOCAL.remove();
    }
}


