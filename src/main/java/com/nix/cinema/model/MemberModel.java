package com.nix.cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nix.cinema.model.base.BaseModel;
import com.nix.cinema.service.impl.MemberService;

import java.math.BigDecimal;

/**
 * @author 11723
 * 用户
 */
public class MemberModel extends BaseModel<MemberModel> {
    private String username;
    private String password;
    private Integer age;
    private Boolean sex;
    //电话
    private String phone;
    //账户余额
    private BigDecimal balance;
    //头像
    private String img;
    //是管理角色还是用户
    private Boolean admin;

    //用户角色
    private RoleModel role;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

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

    public boolean isSuperAdmin() {
        return MemberService.ADMIN_USERNAME.equals(username);
    }

    @Override
    public String toString() {
        return "MemberModel{" +
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