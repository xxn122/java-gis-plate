package com.example.mygis.mapper;

import com.example.mygis.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface DisplayMapper {
    @Insert("insert into user(username,password,email)" + "values(#{username},#{password},#{email})")
    void add(String username, String password,String email) ;

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Update("update user set username =#{username},password= #{password,email=#{email} where username = #{username}")
    void update(String username,String password,String email);
}
