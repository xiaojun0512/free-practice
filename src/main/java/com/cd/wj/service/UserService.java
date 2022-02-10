package com.cd.wj.service;

import com.cd.wj.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author xj
 * @since 2022-01-25
 */
public interface UserService extends IService<User> {

    String login(User user);

    String addUser(User user);

    String saveUserList();
}
