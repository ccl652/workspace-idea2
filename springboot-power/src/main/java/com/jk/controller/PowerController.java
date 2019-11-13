package com.jk.controller;

import com.jk.model.Powers;
import com.jk.model.Users;
import com.jk.service.PowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈PowerController〉
 *
 * @author Administrator
 * @create 2019/11/4
 * @since 1.0.0
 */
@Controller
public class PowerController {

    @Autowired
    private PowerService powerService;

    @RequestMapping("toIndex")
    public String toIndex(){
        return "index";
    }

    @RequestMapping("queryUserPowerTree")
    @ResponseBody
    public List<Powers> queryUserPowerTree(HttpServletRequest request){
        Users users = (Users) request.getSession().getAttribute("user");
        return powerService.queryUserPowerTree(-1,users.getUserid());
    }

    @RequestMapping("toGetPower")
    public String toGetPower(Integer rid,HttpServletRequest request){
        request.getSession().setAttribute("rid",rid);
        return "rolePower";
    }

    @RequestMapping("queryRolePowerTree")
    @ResponseBody
    public List<Powers> queryRolePowerTree(HttpServletRequest request){
        Integer rid = (Integer) request.getSession().getAttribute("rid");
        return powerService.selectPowerList(rid,-1);
    }

    @RequestMapping("toSetPower")
    @ResponseBody
    public Integer toSetPower (String ids,Integer rid){
        powerService.addRolePower(ids,rid);
        return 1;
    }

    @RequestMapping("toQueryPower")
    public String toQueryPower(){
        return "showPowerList";
    }

    @RequestMapping("queryPower")
    @ResponseBody
    public List<Powers> queryPower(){
        return powerService.queryPower();
    }



}