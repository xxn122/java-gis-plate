package com.example.mygis.pojo;



import lombok.Data;
import lombok.Getter;
//设置用户
import java.time.LocalDateTime;
@Data
public class User {
    @Getter
    private Integer id;//主键ID
    @Getter
    private String username;//用户名
    @Getter
    private String password;//密码
    @Getter
    private String nickname;//昵称
    @Getter
    private String email;//邮箱
    @Getter
    private String userPic;//用户头像地址
    @Getter
    private LocalDateTime createTime;//创建时间
    @Getter
    private LocalDateTime updateTime;//更新时间
    @Getter
    private Boolean isAdmin;//身份


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
