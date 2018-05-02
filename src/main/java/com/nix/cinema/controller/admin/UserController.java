package com.nix.cinema.controller.admin;

import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.model.UserModel;
import com.nix.cinema.service.impl.UserService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kiss
 * @date 2018/05/01 23:53
 */
@RestController
@RequestMapping("/admin/user")
public class UserController implements AdminController {
    @Autowired
    private UserService userService;
    /**
     * 管理员添加一个用户
     * */
    @PostMapping("/add")
    public ReturnObject createUser(@ModelAttribute("/") UserModel user) throws Exception {
        Assert.notNull(user.getUsername(),"用户名不能为空");
        Assert.notNull(user.getPassword(),"密码不能为空");
        userService.add(user);
        return ReturnUtil.success(userService.findByUsername(user.getUsername()));
    }
    @PostMapping("/delete")
    public ReturnObject delete(@RequestParam("ids") Integer[] ids) throws Exception {
        userService.delete(ids);
        return ReturnUtil.success();
    }
    @PostMapping("/update")
    public ReturnObject update(@ModelAttribute("/") UserModel user) throws Exception {
        Assert.notNull(user.getId(),"id不能为空");
        userService.update(user);
        return ReturnUtil.success(user);
    }
    @PostMapping("/list")
    public ReturnObject list(Pageable<UserModel> pageable) {
        return ReturnUtil.success(pageable.getList(userService));
    }
}
