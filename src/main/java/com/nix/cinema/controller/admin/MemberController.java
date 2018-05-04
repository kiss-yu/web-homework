package com.nix.cinema.controller.admin;

import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.model.MemberModel;
import com.nix.cinema.service.impl.MemberService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kiss
 * @date 2018/05/01 23:53
 */
@RestController
@RequestMapping("/admin/member")
@AdminController
public class MemberController {
    @Autowired
    private MemberService userService;
    /**
     * 管理员添加一个用户
     * */
    @PostMapping("/add")
    public ReturnObject createUser(@ModelAttribute("/") MemberModel user) throws Exception {
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
    public ReturnObject update(@ModelAttribute("/") MemberModel user) throws Exception {
        Assert.notNull(user.getId(),"id不能为空");
        //如果密码未修改
        if (user.getPassword() != null && user.getPassword().isEmpty()) {
            user.setPassword(null);
        }
        //任何情况下将余额设置为空不进行修改
        user.setBalance(null);
        //usernmae不进行修改
        user.setUsername(null);
        userService.update(user);
        return ReturnUtil.success(user);
    }
    @PostMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<MemberModel> pageable) {
        return ReturnUtil.success(pageable.getList(userService));
    }
}
