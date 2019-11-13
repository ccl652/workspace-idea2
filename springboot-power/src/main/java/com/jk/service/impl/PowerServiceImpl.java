package com.jk.service.impl;

import com.jk.mapper.PowerMapper;
import com.jk.model.Powers;
import com.jk.service.PowerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈PowerServiceImpl〉
 *
 * @author Administrator
 * @create 2019/11/4
 * @since 1.0.0
 */
@Service
public class PowerServiceImpl implements PowerService {

    @Autowired
    private PowerMapper powerMapper;


    @Override
    public List<Powers> queryUserPowerTree(int pid,Integer userid) {
        List<Powers> powerList1 = powerMapper.queryUserPowerByPid(pid,userid);
        if(powerList1 != null && powerList1.size()>0){
//			循环集合，
            for (int i = 0; i < powerList1.size(); i++) {
//				根据当前节点id查询子节点列表
                List<Powers> powerList2 = queryUserPowerTree(powerList1.get(i).getId(),userid);
//				则将子节点列表set到当前节点对象的children属性中
                powerList1.get(i).setChildren(powerList2);
            }
        }

        return powerList1;
    }

    @Override
    public List<Powers> selectPowerList(Integer rid, int pid) {
        List<Powers> powerList1 = powerMapper.selectPowerByPid(pid);
        List<Integer> powerList2 = powerMapper.queryPowerByRid(rid);

        if(powerList1 != null && powerList1.size()>0){
            for (int i = 0; i < powerList1.size(); i++) {
                List<Powers> powerList3 = selectPowerList(rid,powerList1.get(i).getId());
                powerList1.get(i).setChildren(powerList3);


                for (int j = 0; j < powerList2.size(); j++) {
                    if(powerList3.size() <=0 && powerList1.get(i).getId()==powerList2.get(j)){
                        powerList1.get(i).setChecked(true);
                    }
                }
            }
        }
        return powerList1;
    }

    @Override
    public void addRolePower(String ids, Integer rid) {
        powerMapper.deletePowerByRid(rid);
        powerMapper.addRolePower(ids.split(","),rid);
    }

    @Override
    public List<Powers> queryPower() {
        return powerMapper.queryPower();
    }
}