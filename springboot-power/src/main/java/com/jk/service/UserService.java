package com.jk.service;

import com.jk.model.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    Map<String, Object> login(Users user);

    List<Users> queryUserList();
}
