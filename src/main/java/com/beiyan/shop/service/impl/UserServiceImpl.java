package com.beiyan.shop.service.impl;

import com.beiyan.shop.dao.UserDao;
import com.beiyan.shop.domain.User;
import com.beiyan.shop.service.RoleService;
import com.beiyan.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或者密码错误.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roleService.getAuthorities(user.getUserId()));

    }


    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findById(Long userId) {
        return userDao.findById(userId);
    }

    @Override
    public int save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }
}
