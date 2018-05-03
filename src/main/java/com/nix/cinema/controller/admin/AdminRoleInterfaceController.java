package com.nix.cinema.controller.admin;

import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.model.RoleInterfaceModel;
import com.nix.cinema.service.impl.RoleInterfaceService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kiss
 * @date 2018/05/03 11:46
 */
@RestController
@AdminController
@RequestMapping("/admin/roleInterface")
public class AdminRoleInterfaceController {

    @Autowired
    private RoleInterfaceService roleInterfaceService;
    @PostMapping("/add")
    public ReturnObject add(@ModelAttribute RoleInterfaceModel roleInterfaceModel) throws Exception {
        roleInterfaceService.add(roleInterfaceModel);
        return ReturnUtil.success(roleInterfaceModel);
    }
}
