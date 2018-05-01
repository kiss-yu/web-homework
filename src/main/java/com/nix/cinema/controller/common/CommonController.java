package com.nix.cinema.controller.common;

import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.model.UserModel;
import com.nix.cinema.service.impl.UserService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kiss
 * @date 2018/05/01 23:52
 * 公共核心controller
 */
@Controller
public class CommonController implements BController {
    @Autowired
    private UserService userService;

    /**
     * 登录接口
     * */
    @Clear
    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "username",defaultValue = "") String username,
                              @RequestParam(value = "password",defaultValue = "") String password, HttpServletRequest request) {
        UserModel user = userService.login(username,password,request);
        if (user == null) {
            return "redirect:/loginPage";
        }
        if (user.getAdmin()) {
            return "redirect:/admin";
        } else {
            return "redirect:/";
        }
    }

    /**
     * 获取当前用户
     * */
    @Clear
    @ResponseBody
    @GetMapping("/currentUser")
    public ReturnObject currentUser() {
        return ReturnUtil.success(UserCache.currentUser());
    }
}
