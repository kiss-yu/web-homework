package com.nix.cinema.controller;

import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.model.UserModel;
import com.nix.cinema.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kiss
 * @date 2018/05/01 23:52
 */
@org.springframework.stereotype.Controller
public class CommonController implements Controller {
    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * */
    @Clear
    @PostMapping(value = {"/login","/user/login","/admin/login"})
    public String login(@RequestParam("username") String username,
                              @RequestParam("password") String password, HttpServletRequest request) {
        UserModel user = userService.login(username,password,request);
        if (user.getAdmin()) {
            return "/admin/index.html";
        } else {
            return "/member/index.html";
        }
    }
}
