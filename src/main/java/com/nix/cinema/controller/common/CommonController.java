package com.nix.cinema.controller.common;

import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.service.impl.SystemService;
import com.nix.cinema.service.impl.UserService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Kiss
 * @date 2018/05/01 23:52
 * 公共核心controller
 */
@Controller
public class CommonController {
    @Autowired
    private UserService userService;
    @Autowired
    private SystemService systemService;

    /**
     * 登录接口
     * */
    @ResponseBody
    @PostMapping(value = "/login")
    public ReturnObject login(@RequestParam(value = "username",defaultValue = "") String username,
                              @RequestParam(value = "password",defaultValue = "") String password, HttpServletRequest request) {
        return ReturnUtil.success(userService.login(username,password,request));
    }

    /**
     * 获取当前用户
     * */
    @ResponseBody
    @GetMapping("/currentUser")
    public ReturnObject currentUser() {
        return ReturnUtil.success(UserCache.currentUser());
    }

    @ResponseBody
    @GetMapping("/system/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        systemService.getCaptcha(request,response);
    }
}
