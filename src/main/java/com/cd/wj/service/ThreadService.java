package com.cd.wj.service;

import com.cd.wj.entity.User;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public interface ThreadService {
    /**
     * 执行异步任务
     * @param userList
     */
    void executeAsync(List<User> userList, CountDownLatch countDownLatch);
}
