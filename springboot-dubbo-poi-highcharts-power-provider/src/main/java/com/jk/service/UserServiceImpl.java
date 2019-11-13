package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.Users;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈UserServiceImpl〉
 *
 * @author chenchunlan
 * @create 2019/11/4
 * @since 1.0.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Users queryUserByName(String username){
        Users user = (Users) userMapper.selectUserByName(username);
        return user;
    }

    @Override
    public List<Users> queryUserList() {
        return userMapper.queryUserList();
    }


}