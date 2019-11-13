package com.jk.service.impl;

import com.jk.mapper.UserMapper;
import com.jk.model.Users;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Map<String, Object> login(Users user) {
        Map<String, Object> map = new HashMap<>();
        int flag = 1; //1:用户不存在  2：密码错误   3：登录成功
        List<Users> userList = userMapper.selectUserByName(user.getUsername());
        if (userList != null && userList.size() > 0) {
            Users userDB = userList.get(0);
            flag = 2;
            if (user.getPwd().equals(userDB.getPwd())) {
                flag = 3;
                map.put("userObj", userDB);
            }
        }
        map.put("flag", flag);
        return map;
    }

    @Override
    public List<Users> queryUserList() {
        return userMapper.queryUserList();
    }


}