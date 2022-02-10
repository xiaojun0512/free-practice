package com.cd.wj.service.impl;

import com.cd.wj.entity.User;
import com.cd.wj.mapper.UserMapper;
import com.cd.wj.service.ThreadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

@Slf4j
@Service
public class ThreadServiceImpl implements ThreadService {
    private final UserMapper userMapper;

    public ThreadServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync(List<User> userList, CountDownLatch countDownLatch) {
        log.info("start executeAsync");
        try{
            userMapper.saveUserList(userList);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            // 很关键, 无论上面程序是否异常必须执行countDown,否则await无法释放
            countDownLatch.countDown();
        }
        log.info("end executeAsync");
    }
}

