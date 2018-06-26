package com.beiyan.shop.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

/**
 * @author： BeiYan
 * @Date： 2018-06-25 16:15
 * @Description:
 */

public interface RoleService {

    List<SimpleGrantedAuthority> getAuthorities(long userId);
}

