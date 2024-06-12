package com.example.mygis.Service;

import com.example.mygis.pojo.Admin;

public interface AdminService {
    Admin findByUsername(String username);


    void register(String username, String password);
}
