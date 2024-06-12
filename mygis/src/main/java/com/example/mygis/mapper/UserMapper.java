package com.example.mygis.mapper;

import com.example.mygis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    //注册添加
    @Insert("insert into user(username,password,create_time,update_time)" + "values(#{username},#{password},now(),now())")
     void add(String username, String password) ;

    //根据用户名查询用户
    @Select("select * from user where username = #{username} ")
    User findByUsername(String username);
    //展示用户信息
    @Select("select * from user")
    List<User> display();
    @Update("update user set username =#{username},password= #{password},email=#{email} where username = #{username}")
    void update(String username,String password,String email);
}
