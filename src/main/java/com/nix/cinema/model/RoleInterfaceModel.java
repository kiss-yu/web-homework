package com.nix.cinema.model;

import com.nix.cinema.model.base.BaseModel;

/**
 * @author Kiss
 * @date 2018/05/01 19:02
 * 接口
 */
public class RoleInterfaceModel extends BaseModel<RoleInterfaceModel> {
    //controller类
    private String controller;
    //controller方法名
    private String method;

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
