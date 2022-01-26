package com.cd.wj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cd.wj.entity.User;
import com.cd.wj.mapper.UserMapper;
import com.cd.wj.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cd.wj.utils.TokenUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author xj
 * @since 2022-01-25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public String login(User user) {
        String token = "";
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",user.getName()).eq("password",user.getPassword());
        User u = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(u)) {
            token = TokenUtil.getToken(u);
        }
        return token;
    }
}
