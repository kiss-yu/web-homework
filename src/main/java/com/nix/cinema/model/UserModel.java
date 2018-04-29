package com.nix.cinema.model;

import com.nix.cinema.model.base.BaseModel;

/**
 * @author 11723
 */
public class UserModel extends BaseModel<UserModel> {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}