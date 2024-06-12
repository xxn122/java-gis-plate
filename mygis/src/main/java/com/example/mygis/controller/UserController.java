package com.example.mygis.controller;

import com.example.mygis.Service.UserService;
import com.example.mygis.mapper.DisplayMapper;
import com.example.mygis.mapper.UserMapper;
import com.example.mygis.pojo.Result;
import com.example.mygis.pojo.User;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    //注册端口

    @PostMapping("/register")
    @CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
    public Result register( User user){
        String username = user.getUsername();
        String password = user.getPassword();

            User u = userService.findByUsername(username);
            if (u == null){
                //没有占用
                //注册
                userService.register(username,password);
                return Result.success();
            }else {
                //占用
                return Result.error("用户名已经被占用");
            }

    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173/", maxAge = 3600)
    public  Result login (User user){
        String username = user.getUsername();
        String password = user.getPassword();
        User l= userService.findByUsername(username);
        if (l==null){
            return Result.error("用户未注册");
        }
        if (password.equals(l.getPassword())){
            return Result.success();
        }else {
            return Result.error("出错了");
        }
    }
    @PostMapping ("/userInfo")
    public Result<User> userInfo( User user){
        //根据用户名查询用户
        String username = user.getUsername();
        User user1 = userService.findByUsername(username);
        return Result.success(user1);
    }
    @PostMapping ("/displayuser")
    //@CrossOrigin(origins = "http://localhost:5173/admin", maxAge = 3600)

    public List userdisplay(Model m, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        List<User> alluser = userMapper.display();
        m.addAttribute("alluser",alluser);
        return alluser;
    }
    @PostMapping("/update")
    public Result update(User user,HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();
        User u = userService.findByUsername(username);
        if (u==null){
            return Result.error("未查找到该用户");
        }else {
            userMapper.update(username,password,email);
            return Result.success();
        }
    }
    @PostMapping("/search")
    public Result search(String username, HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin", "*");
        User u = userService.findByUsername(username);
        if (u == null){
            return Result.error("未查找到该用户");
        }else {
            return Result.success(u);
        }
    }

}
