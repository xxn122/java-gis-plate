package com.example.mygis.mapper;
import com.example.mygis.pojo.Admin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminMapper {
    //管理员注册添加
    @Insert("insert into admin (username,password,create_time)"+"values (#{username},#{password},now())")
    void add(String username, String password);
    @Select("select * from admin where username = #{username} ")
    Admin findByUsername(String username);
}
