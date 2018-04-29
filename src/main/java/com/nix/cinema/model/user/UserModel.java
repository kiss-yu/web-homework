package com.nix.cinema.model.user;

import com.nix.cinema.model.base.BaseModel;

/**
 * @author 11723
 * 用户
 */
public class UserModel extends BaseModel<UserModel> {
    private String username;
    private String password;
    private Integer age;
    private boolean sex;
    //账户余额
    private Integer balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}