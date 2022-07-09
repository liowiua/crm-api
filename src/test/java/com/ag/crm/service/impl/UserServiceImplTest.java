package com.ag.crm.service.impl;

import com.ag.crm.domain.ResultVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    UserServiceImpl userService;

    @Test
    public void info() {
//        System.out.println(userService.info("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyTmFtZSI6ImFkbWluIiwiZXhwIjoxNjU1NDI2MTEwfQ.hmroR5gMKCcze9Sb0I2qK46vlDa0x6FSeXeiKsvhQOQ"));
    }

    @Test
    public void selectAllManager() {
        ResultVO vo =  userService.selectAllManager();
        System.out.println(vo);

    }
}