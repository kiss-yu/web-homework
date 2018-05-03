package com.nix.cinema.controller.admin;

import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.model.RoleModel;
import com.nix.cinema.service.impl.RoleService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Kiss
 * @date 2018/05/03 11:42
 */
@RestController
@AdminController
@RequestMapping("/admin/role")
public class AdminRoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/checkName")
    public boolean checkName(@RequestParam("name") String name) {
        return roleService.findByName(name) == null;
    }

    @PostMapping("/add")
    public ReturnObject add(@ModelAttribute RoleModel roleModel) throws Exception {
        return ReturnUtil.success(roleService.add(roleModel));
    }
}
