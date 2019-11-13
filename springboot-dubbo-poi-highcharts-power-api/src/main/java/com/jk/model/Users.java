package com.jk.model;

import java.io.Serializable;

/**
 * 〈用户表〉<br>
 * 〈Users〉
 *
 * @author chenchunlan
 * @create 2019/11/4
 * @since 1.0.0
 */
public class Users implements Serializable {

    private Integer userid;

    private String username;

    private String password;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}