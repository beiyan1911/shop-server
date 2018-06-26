package com.beiyan.shop.controller;

import com.beiyan.shop.domain.LoginUser;
import com.beiyan.shop.domain.User;
import com.beiyan.shop.service.UserService;
import com.beiyan.shop.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class RegisterController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/register", method = RequestMethod.POST)
    public String registerUser(@RequestBody @Valid LoginUser loginUser) {

        User user = userService.findByUsername(loginUser.getUsername());
        if (user != null) {
            return ResultUtil.errMsg("用户名已存在");
        } else {
            user = new User();
            user.setUsername(loginUser.getUsername());
            user.setPassword(loginUser.getPassword());
            userService.save(user);
            return ResultUtil.success;
        }


    }

}
