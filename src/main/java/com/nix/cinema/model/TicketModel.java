package com.nix.cinema.model;

import com.nix.cinema.model.base.BaseModel;

import java.math.BigDecimal;

/**
 * @author Kiss
 * @date 2018/04/29 20:16
 * 电影票
 */
public class TicketModel extends BaseModel<TicketModel> {
    private String ticketSn;
    //介绍
    private String introduce;
    private BigDecimal price;
    //所属电影院
    private CinemaModel cinema;
    //属于哪个电影
    private MovieModel movie;
    //模拟房间
    private String room;

    //编号
    private Integer sn;

    public String getTicketSn() {
        return ticketSn;
    }

    public void setTicketSn(String ticketSn) {
        this.ticketSn = ticketSn;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public CinemaModel getCinema() {
        return cinema;
    }

    public void setCinema(CinemaModel cinema) {
        this.cinema = cinema;
    }

    public MovieModel getMovie() {
        return movie;
    }

    public void setMovie(MovieModel movie) {
        this.movie = movie;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
