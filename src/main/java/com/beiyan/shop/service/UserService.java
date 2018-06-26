package com.beiyan.shop.service;


import com.beiyan.shop.domain.User;

import java.util.List;

public interface UserService {

    int save(User user);

    List<User> findAll();

    User findByUsername(String username);

    User findById(Long userId);
}
