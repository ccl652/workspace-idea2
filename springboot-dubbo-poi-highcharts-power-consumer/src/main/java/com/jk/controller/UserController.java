package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.Users;
import com.jk.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Reference
    private UserService userService;

    @RequestMapping("toLogin")
    public String toLogintoLogin(){
        return "login";
    }

    @RequestMapping("login")
    public String login(HttpServletRequest request, Model model){
        String shiroLoginFailure = request.getAttribute("shiroLoginFailure").toString();

        if(UnknownAccountException.class.getName().equals(shiroLoginFailure) || AuthenticationException.class.getName().equals(shiroLoginFailure)){
            model.addAttribute("msg","用户名输入错误 UnknownAccountException");
        }else if (IncorrectCredentialsException.class.getName().equals(shiroLoginFailure)){
            model.addAttribute("msg","用户名输入错误 IncorrectCredentialsException");
        }

        return "login";
    }

   /* @RequestMapping("login")
    @ResponseBody
    public Integer login(Users user ,HttpServletRequest request) {
            Map<String, Object> map = userService.login(user);
            Users userObj = (Users) map.get("userObj");
            if (userObj != null) {
                request.getSession().setAttribute("user",userObj);
            }
        return (Integer) map.get("flag");
    }
*/
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