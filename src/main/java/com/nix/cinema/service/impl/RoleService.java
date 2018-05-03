package com.nix.cinema.service.impl;

import com.nix.cinema.dao.RoleInterfaceMapper;
import com.nix.cinema.dao.RoleMapper;
import com.nix.cinema.model.RoleInterfaceModel;
import com.nix.cinema.model.RoleModel;
import com.nix.cinema.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Kiss
 * @date 2018/05/01 22:56
 * 角色service
 */
@Service
public class RoleService extends BaseService<RoleModel> {
    @Autowired
    private RoleMapper roleMapper;
    private RoleInterfaceMapper roleInterfaceMapper;
    public RoleModel findByName(String name) {
        try {
            return roleMapper.findByOneField("name",name).get(0);
        }catch (Exception e) {
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public RoleModel add(RoleModel model) throws Exception{
        super.add(model);
        for (RoleInterfaceModel roleInterfaceModel:model.getRoleInterfaces()) {
            roleMapper.insertRoleMiddleInterface(model.getId(),roleInterfaceModel.getId());
        }
        return model;
    }
}
