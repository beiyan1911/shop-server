package com.beiyan.shop.service.impl;

import com.beiyan.shop.dao.RoleDao;
import com.beiyan.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author： BeiYan
 * @Date： 2018-06-25 16:15
 * @Description:
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Override
    public List<SimpleGrantedAuthority> getAuthorities(long userId) {
        return roleDao.getAuthorities(userId);
    }
}

