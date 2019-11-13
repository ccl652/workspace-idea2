package com.jk.mapper;

import com.jk.model.Powers;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PowerMapper {
    List<Powers> queryUserPowerByPid(@Param("pid") int pid, @Param("uid") Integer userid);

    List<Powers> selectPowerByPid(@Param("pid")int pid);

    List<Integer> queryPowerByRid(@Param("rid")Integer rid);

    void deletePowerByRid(@Param("rid")Integer rid);

    void addRolePower(@Param("pids") String[] split, Integer rid);


    List<Powers> queryPower();
}
