package com.nix.cinema.model;

import com.nix.cinema.model.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author Kiss
 * @date 2018/04/29 20:31
 * 付款单
 */
public class PaymentModel extends BaseModel<PaymentModel> {
    private String sn;

    private UserModel user;
    //金额
    private BigDecimal amount;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
