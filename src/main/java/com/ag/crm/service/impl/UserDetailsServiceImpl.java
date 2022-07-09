package com.ag.crm.service.impl;

import com.ag.crm.dao.UserMapper;
import com.ag.crm.domain.LoginUser;
import com.ag.crm.domain.Module;
import com.ag.crm.domain.UserDetailPermission;
import com.ag.crm.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //查询用户信息
        User user = userMapper.selectByUserName(username);
        //如果没有查询到用户就抛出异常
        if(Objects.isNull(user)){
            throw new RuntimeException("用户名或者密码错误");
        }

        //TODO


        List<String> list = userMapper.selectPermissionByUsername(username)
                .getModules().stream().map(Module::getOptValue).collect(Collectors.toList());
        UserDetailPermission userDetailPermission = userMapper.selectPermissionByUsername(username);
        //把数据封装成UserDetails返回
        int a=0;
        return new LoginUser(user,list);
    }
}
