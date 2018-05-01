package com.nix.cinema.controller;

import com.nix.cinema.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kiss
 * @date 2018/04/25 11:30
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/login")
    public void login(@RequestParam("username") String username,
                      @RequestParam("password") String password, HttpServletRequest request) {
        userService.login(username,password,request);
    }
}
