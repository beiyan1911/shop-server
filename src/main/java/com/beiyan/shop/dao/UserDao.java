package com.beiyan.shop.dao;


import com.beiyan.shop.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

public interface UserDao {
    @Select("select user_id userId,username,password from user where username = #{username}")
    User findByUsername(String username);

    @Select("select * from user")
    List<User> findAll();

    @Select("select * from user where user_id = #{userId}")
    User findById(long userId);

    @Insert("insert into user (username, password) values" +
            " (#{username}, #{password})")
    int save(User user);

    @Select("select role_name role from role")
    List<SimpleGrantedAuthority> getAuthorities(String username);
}

