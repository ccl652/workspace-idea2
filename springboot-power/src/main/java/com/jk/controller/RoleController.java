package com.jk.controller;

import com.jk.model.Roles;
import com.jk.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈RoleController〉
 *
 * @author Administrator
 * @create 2019/11/4
 * @since 1.0.0
 */
@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("toGetRole")
    public String toGetRole2(Integer userid, HttpServletRequest request){
        request.getSession().setAttribute("userid",userid);
        List<Roles> roleList = roleService.selectRoleList(userid);
        request.setAttribute("roleList", roleList);
        return "userRole";
    }

    @RequestMapping("toSetRole")
    @ResponseBody
    public int toSetRole(HttpServletRequest request,String rids){
        Integer userid = (Integer) request.getSession().getAttribute("userid");
        roleService.addUserRole(userid,rids);
        return 1;
    }

    @RequestMapping("toQueryRole")
    public String toQueryRole(){
        return "showRoleList";
    }

    @RequestMapping("queryRoleList")
    @ResponseBody
    public List<Roles> queryRoleList(){
        return roleService.queryRoleList();
    }





}