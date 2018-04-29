package com.nix.cinema.model.user;

import com.nix.cinema.model.TicketModel;
import com.nix.cinema.model.base.BaseModel;

import java.util.List;

/**
 * @author Kiss
 * @date 2018/04/29 20:13
 * 订单
 */
public class OrderModel extends BaseModel<OrderModel> {
    public enum Status{
        //等待付款
        waitPayment,
        //等待取票
        WaitingToCollectTheTickets,
        //交易完成
        tradingComplete,
        //等待退款
        WaitingForRefund,
        //退款完成
        refundComplete
    }
    private String orderSn;
    private UserModel user;
    //应付金额
    private Integer payableAmount;
    //已付金额
    private Integer paidAmount;
    //购票列表
    private List<TicketModel> tickets;
    //订单状态
    private String status;

    public String getOrderSn() {
        return orderSn;
    }

    public void setOrderSn(String orderSn) {
        this.orderSn = orderSn;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Integer getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Integer payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    public List<TicketModel> getTickets() {
        return tickets;
    }

    public void setTickets(List<TicketModel> tickets) {
        this.tickets = tickets;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setEnumStatus(Status status) {
        this.status = status.name();
    }

    public Status getEnumStatus() {
        return Status.valueOf(status);
    }
}
