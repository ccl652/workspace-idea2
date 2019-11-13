package com.jk.mapper;

import com.jk.model.Roles;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Roles> selectRoleList();

    List<Integer> selectUserRoleByUserid(@Param("uid") Integer userid);

    void deleteUserRole(@Param("uid") Integer userid);

    void addUserRole(@Param("uid") Integer userid, @Param("rids") String[] rids);

}
