package com.nix.cinema.controller;

import com.nix.cinema.common.annotation.Clear;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kiss
 * @date 2018/05/01 20:23
 */
@org.springframework.stereotype.Controller
public class RouterController implements Controller {

    /**
     * 用户主页
     * */
    @GetMapping("/")
    @Clear
    public String userIndex() {
        return "/member/index.html";
    }
    /**
     * 管理员主页
     * */
    @GetMapping("/admin")
    public String adminIndex() {
        return "/admin/index.html";
    }
}
