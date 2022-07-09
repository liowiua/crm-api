package com.ag.crm.task;

import com.ag.crm.service.CustomerService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


@Component
public class JobTask {

    @Resource
    private CustomerService customerService;


    /**
     * 每2秒执行一次
     * @param
     * @return void
     */
    // @Scheduled(cron = "0/2 * * * * ?")
    public void job() {
        System.out.println("定时任务开始执行 --> " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 调用需要被定时执行的方法
        customerService.updateCustomerState();
    }

}
