package com.example.mygis.controller;

import com.example.mygis.Service.AdminService;
import com.example.mygis.pojo.Admin;
import com.example.mygis.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

        @Autowired
        private AdminService adminService;

        @PostMapping("/login")
        @CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
        public Result login (Admin admin){
            String username = admin.getUsername();
            String password = admin.getPassword();
            Admin l= adminService.findByUsername(username);
            if (l==null){
                return Result.error("用户未注册");
            }
            if (password.equals(l.getPassword())){
                return Result.success();
            }else {
                return Result.error("出错了");
            }
        }

}
