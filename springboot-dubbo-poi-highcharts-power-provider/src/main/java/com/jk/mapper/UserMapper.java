package com.jk.mapper;

import com.jk.model.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    Users selectUserByName(@Param("un") String username);

    List<Users> queryUserList();
}
