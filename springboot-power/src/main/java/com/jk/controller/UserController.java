package com.jk.controller;

import com.jk.model.Users;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈UserController〉
 *
 * @author Administrator
 * @create 2019/11/4
 * @since 1.0.0
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogin(){
        return "login";
    }


    @RequestMapping("login")
    @ResponseBody
    public Integer login(Users user ,HttpServletRequest request) {
            Map<String, Object> map = userService.login(user);
            Users userObj = (Users) map.get("userObj");
            if (userObj != null) {
                request.getSession().setAttribute("user",userObj);
            }
        return (Integer) map.get("flag");
    }

    @RequestMapping("toQueryUser")
    public String toQueryUser(){
        return "showUserList";
    }

    @RequestMapping("queryUserList")
    @ResponseBody
    public List<Users> queryUserList(){
        return userService.queryUserList();
    }


}