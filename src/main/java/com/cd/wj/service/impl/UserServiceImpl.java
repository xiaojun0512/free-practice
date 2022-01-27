package com.cd.wj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cd.wj.entity.User;
import com.cd.wj.mapper.UserMapper;
import com.cd.wj.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cd.wj.utils.MD5Util;
import com.cd.wj.utils.TokenUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.UUID;

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
        wrapper.eq("name",user.getName()).eq("password",getMd5(user.getName(), user.getPassword()));
        User u = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(u)) {
            token = TokenUtil.getToken(u);
        }
        return token;
    }

    @Override
    public String addUser(User user) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name",user.getName());
        User one = baseMapper.selectOne(wrapper);
        if (ObjectUtils.isNotEmpty(one)) {
            throw new RuntimeException("用户已存在");
        }
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(LocalDateTime.now());
        user.setPassword(getMd5(user.getName(), user.getPassword()));
        if (baseMapper.insert(user) > 0) {
            return "新增用户成功";
        } else {
            return "新增用户失败";
        }
    }

    /**
     * 设置md5生成方式为:(姓名+密码)后的字符串生成md5码
     * 具体生成方法自行调整,越复杂越安全
     * @param name 姓名
     * @param password 密码
     * @return md5
     */
    private String getMd5(String name,String password) {
        String md5 = MD5Util.getMD5ByBytes((name + password).getBytes(StandardCharsets.UTF_8));
        char c = password.charAt(0);
        char c1 = password.charAt(password.length() - 1);
        char c2 = password.charAt(password.length() / 3);
        md5 = c + md5 + c1;
        md5 = md5.substring(0,md5.length() / 3) + c2 + md5.substring(md5.length() / 3);
        return MD5Util.getMD5ByBytes(md5.getBytes(StandardCharsets.UTF_8));
    }
}
