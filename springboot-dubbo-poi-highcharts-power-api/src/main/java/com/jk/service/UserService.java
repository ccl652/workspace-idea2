package com.jk.service;

import com.jk.model.Users;

import java.util.List;
import java.util.Map;

public interface UserService {
    Users queryUserByName(String username);

    List<Users> queryUserList();
}
