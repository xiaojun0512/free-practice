package com.cd.wj.config;

import com.cd.wj.entity.User;
import com.cd.wj.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@Configuration
@EnableAsync
public class MyScheduled {
    @Autowired
    private UserMapper userMapper;

    @Async("asyncServiceExecutor")
    @Scheduled(cron = "0/5 * * * * ?")
    public void getUserName() {
        User user = userMapper.selectById("f53ad2a1-9b68-4721-8997-f2a9f0878c63");
        System.out.println(user.getName());
    }
}
