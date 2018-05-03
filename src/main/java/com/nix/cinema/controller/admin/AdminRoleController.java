package com.nix.cinema.controller.admin;

import com.nix.cinema.common.Pageable;
import com.nix.cinema.common.ReturnObject;
import com.nix.cinema.common.annotation.AdminController;
import com.nix.cinema.model.RoleInterfaceModel;
import com.nix.cinema.model.RoleModel;
import com.nix.cinema.model.UserModel;
import com.nix.cinema.service.impl.RoleService;
import com.nix.cinema.util.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public ReturnObject add(
            @ModelAttribute RoleModel roleModel,
            @RequestParam(value = "roleInterfaceId",required = false) Integer[] roleInterfaceId) throws Exception {
        ArrayList<RoleInterfaceModel> roleInterfaceModels = new ArrayList<>();
        if (roleInterfaceId != null && roleInterfaceId.length > 0) {
            for (Integer id:roleInterfaceId) {
                RoleInterfaceModel model = new RoleInterfaceModel();
                model.setId(id);
                roleInterfaceModels.add(model);
            }
            roleModel.setRoleInterfaces(roleInterfaceModels);
        }
        return ReturnUtil.success(roleService.add(roleModel));
    }

    @GetMapping("/view")
    public ReturnObject select(@RequestParam("id") Integer id) {
        return ReturnUtil.success(roleService.findById(id));
    }

    @GetMapping("/list")
    public ReturnObject list(@ModelAttribute Pageable<RoleModel> pageable) throws Exception {
        Map additionalData = new HashMap();
        additionalData.put("total",roleService.count());
        return ReturnUtil.success(null,pageable.getList(roleService),additionalData);
    }
}
