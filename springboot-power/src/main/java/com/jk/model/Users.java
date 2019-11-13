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

    private String pwd;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getPwd() {
        return pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "Users{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}