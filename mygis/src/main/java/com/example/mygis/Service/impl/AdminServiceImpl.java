package com.example.mygis.Service.impl;

import com.example.mygis.Service.AdminService;
import com.example.mygis.mapper.AdminMapper;
import com.example.mygis.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    public Admin findByUsername(String username){
        return adminMapper.findByUsername(username);
    }

    @Override
    public  void  register(String username, String password){
        adminMapper.add(username,password);
    }
}
