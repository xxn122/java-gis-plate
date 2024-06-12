package com.example.mygis.Service;

import com.example.mygis.pojo.User;

import java.util.List;

public interface UserService {
    //注册


    //根据用户名查询用户
    User findByUsername(String username);


    void register(String username, String password);
    void  update(String username, String password,String email);
    List<User> display();
}
