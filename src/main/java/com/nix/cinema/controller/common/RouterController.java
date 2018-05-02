package com.nix.cinema.controller.common;

import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Kiss
 * @date 2018/05/01 20:23
 * 路由controller
 */
@Controller
public class RouterController implements PublicController {

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
        return "/admin/login.html";
    }
}
