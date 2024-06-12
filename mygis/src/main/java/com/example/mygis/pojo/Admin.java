package com.example.mygis.pojo;

import lombok.Getter;

import java.time.LocalDateTime;

public class Admin {
    @Getter
    private Integer id;//主键ID
    @Getter
    private String username;//用户名
    @Getter
    private String password;//密码
    private String email;//邮箱
    private String phone;//电话号码
    private Boolean Sex;//性别
    private LocalDateTime create_Time;//创建时间


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
