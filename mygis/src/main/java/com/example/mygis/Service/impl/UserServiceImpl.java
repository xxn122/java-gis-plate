package com.example.mygis.Service.impl;

import com.example.mygis.mapper.UserMapper;
import com.example.mygis.pojo.User;
import com.example.mygis.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper usermapper;
    @Override
    public User findByUsername(String username) {

        return usermapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password){
        //添加
        usermapper.add(username,password);
    }
    @Override
    public  void update(String username, String password,String email){
        usermapper.update(username,password,email);
    }
    @Override
    public List<User> display(){
        usermapper.display();
        return null;
    }
}
