package com.jk.service;

import com.jk.model.Roles;

import java.util.List;

public interface RoleService {
    List<Roles> selectRoleList(Integer userid);

    void addUserRole(Integer userid, String rids);

    List<Roles> queryRoleList();
}
