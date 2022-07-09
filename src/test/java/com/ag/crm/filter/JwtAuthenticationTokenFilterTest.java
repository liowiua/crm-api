package com.ag.crm.filter;


import com.ag.crm.domain.LoginUser;
import com.ag.crm.utils.RedisCache;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtAuthenticationTokenFilterTest {


    @Autowired
    private RedisCache redisCache;



    @Test
    void test(){
        String redisKey = "login:" + 10;
        LoginUser loginUser = redisCache.getCacheObject(redisKey);
        System.out.println(loginUser);

        System.out.println(redisCache.deleteObject("login:"+10));
    }


}