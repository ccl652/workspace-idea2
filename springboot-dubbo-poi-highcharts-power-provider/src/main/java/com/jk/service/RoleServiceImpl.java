package com.jk.service;

import com.jk.mapper.RoleMapper;
import com.jk.model.Roles;
import com.jk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import com.alibaba.dubbo.config.annotation.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈RoleServiceImpl〉
 *
 * @author Administrator
 * @create 2019/11/4
 * @since 1.0.0
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Roles> selectRoleList(Integer userid) {
        List<Roles> list1 = roleMapper.selectRoleList();
        List<Integer> list2 = roleMapper.selectUserRoleByUserid(userid);
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if(list1.get(i).getRokid()==list2.get(j)){
                    list1.get(i).setChecked(true);
                }
            }
        }
        return list1;
    }

    @Override
    public void addUserRole(Integer userid, String rids) {
        roleMapper.deleteUserRole(userid);
        roleMapper.addUserRole(userid,rids.split(","));
    }

    @Override
    public List<Roles> queryRoleList() {
        return roleMapper.selectRoleList();
    }


}