package com.ag.crm.controller;

import com.ag.crm.domain.ResultVO;
import com.ag.crm.domain.User;
import com.ag.crm.service.LoginServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/login")
    public ResultVO login(@RequestBody User user){
        //登录
        return loginServcie.login(user);
    }

//    @PreAuthorize("@ex.hasAuthority('system:dept:list')")
    @RequestMapping("/user/info")
    public ResultVO info(){
        return loginServcie.info();
    }

    @RequestMapping("/user/logout")
    public ResultVO logout(){
        return loginServcie.logout();
    }
}
