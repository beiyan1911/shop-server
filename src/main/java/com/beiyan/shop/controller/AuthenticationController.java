package com.beiyan.shop.controller;

import com.beiyan.shop.config.JwtTokenUtil;
import com.beiyan.shop.domain.AuthToken;
import com.beiyan.shop.domain.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity register(@RequestBody LoginUser loginUser) {

        //check the username password
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getUsername(),
                        loginUser.getPassword()
                )
        );
        //set user authentication info
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate token
        final String token = jwtTokenUtil.generateToken(loginUser.getUsername());
        return ResponseEntity.ok(new AuthToken(token));
    }

}
