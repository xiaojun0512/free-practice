package com.cd.wj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cd.wj.entity.User;
import com.cd.wj.mapper.UserMapper;
import com.cd.wj.service.ThreadService;
import com.cd.wj.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cd.wj.utils.MD5Util;
import com.cd.wj.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author xj
 * @since 2022-01-25
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private ThreadService threadService;

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

    @Override
    @Transactional
    public String saveUserList() {
        int userListSize = 10000;
        //相当于把数据切分为多少份，然后执行多线程入库操作。这里是总数量10000的数据按照每份1000的数据等分为10块数据，
        //实际业务总数据量大于1000且不能整除的情况下，多余的部分为1份，则要再加1，如果总数据量不足1000则直接设置为1
        CountDownLatch countDownLatch = new CountDownLatch(userListSize/1000);

        long start = System.currentTimeMillis();

        List<User> userList = new ArrayList<>();
        for (int i = 0;i < userListSize;i ++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setPassword("123456");
            user.setCreateTime(LocalDateTime.now());
            user.setName("xj"+i);
            userList.add(user);
            //此处的1000与上面的1000保持一致
            if (userList.size() % 1000 == 0) {
                //多线程方法不能和当前方法在同一类中，不然多线程会失效
                threadService.executeAsync(userList, countDownLatch);
                userList = new ArrayList<>();
            }
        }

        try{
            countDownLatch.await(); //保证之前的所有的线程都执行完成，才会走下面的；
            // 这样就可以在下面拿到所有线程执行完的集合结果
        } catch (Exception e) {
            log.error("阻塞异常:"+e.getMessage());
        }

        long end = System.currentTimeMillis();
        log.info("多线程耗时："+(end-start));

        long one = System.currentTimeMillis();
        List<User> list = new ArrayList<>();
        for (int i = 0;i < userListSize;i ++) {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setPassword("123456");
            user.setCreateTime(LocalDateTime.now());
            user.setName("xj" + i);
            list.add(user);
        }
        baseMapper.saveUserList(list);
        long two = System.currentTimeMillis();
        log.info("单线程耗时："+(two-one));

        return "多线程耗时："+(end-start) + "单线程耗时："+(two-one);
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
