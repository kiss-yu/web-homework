package com.nix.cinema.controller;

import com.nix.cinema.common.cache.UserCache;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Kiss
 * @date 2018/05/01 20:23
 */
@Controller
public class RouterController {

    @RequestMapping("/")
    public String userIndex() {
        System.out.println(UserCache.currentUser());
        return "/index.html";
    }
}
