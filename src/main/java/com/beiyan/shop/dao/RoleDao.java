package com.beiyan.shop.dao;

import org.apache.ibatis.annotations.Select;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author： BeiYan
 * @Date： 2018-06-25 16:10
 * @Description:
 */
public interface RoleDao {

    @Select("select r.role_name roleName from user_vs_role ur left join role r on r.role_id = ur.role_id  where ur.user_id = #{userId} ")
    List<SimpleGrantedAuthority> getAuthorities(long userId);
}
