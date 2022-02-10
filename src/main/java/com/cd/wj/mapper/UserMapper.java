package com.cd.wj.mapper;

import com.cd.wj.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* <p>
* 用户信息 Mapper 接口
* </p>
*
* @author xj
* @since 2022-01-25
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

    int saveUserList(List<User> userList);
}
