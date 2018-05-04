package com.nix.cinema.controller.member;

import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.Clear;
import com.nix.cinema.common.annotation.MemberController;
import com.nix.cinema.common.cache.UserCache;
import com.nix.cinema.model.MemberModel;
import com.nix.cinema.service.impl.MemberService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Kiss
 * @date 2018/04/25 11:30
 */
@RestController
@RequestMapping("/member")
@MemberController
public class IndexController {
    @Autowired
    private MemberService userService;

    @Clear
    @PostMapping("/registered")
    public ReturnObject registered(@ModelAttribute MemberModel user, HttpServletRequest request) throws Exception {
        MemberModel insertUser = userService.registered(user,request);
        if (insertUser == null) {
            return ReturnUtil.fail(user);
        }
        return ReturnUtil.success(insertUser);
    }
    /**
     * 用户修改自己的资料
     * */
    @PostMapping("/update")
    public ReturnObject updateUser(@ModelAttribute MemberModel user) throws Exception {
        Assert.isTrue(UserCache.currentUser().getUsername().equals(user.getUsername()),"非法修改");
        userService.update(user);
        return ReturnUtil.success(user);
    }
}
