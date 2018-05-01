package com.nix.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nix.cinema.model.base.BaseModel;
import java.math.BigDecimal;

/**
 * @author 11723
 * 用户
 */
public class UserModel extends BaseModel<UserModel> {
    private String username;
    private String password;
    private Integer age;
    private Boolean sex;
    //账户余额
    private BigDecimal balance;
    //头像
    private String img;
    //是管理角色还是用户
    private Boolean admin;

    //用户角色
    private RoleModel role;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @JsonIgnore
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

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @JsonIgnore
    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public Boolean getSex() {
        return sex;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", balance=" + balance +
                ", img='" + img + '\'' +
                ", admin=" + admin +
                ", role=" + role +
                ", id=" + id +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                '}';
    }
}