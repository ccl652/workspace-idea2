package com.jk.service;

import com.jk.model.Powers;

import java.util.List;

public interface PowerService {
    List<Powers> queryUserPowerTree(int pid, Integer userId);

    List<Powers> selectPowerList(Integer rid, int pid);

    void addRolePower(String ids, Integer rid);

    List<Powers> queryPower();
}
