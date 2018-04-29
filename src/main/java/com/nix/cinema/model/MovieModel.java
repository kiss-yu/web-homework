package com.nix.cinema.model;

import com.nix.cinema.model.base.BaseModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Kiss
 * @date 2018/04/29 20:48
 * 电影
 */
public class MovieModel extends BaseModel<MovieModel> {
    private String movieSn;
    //上映时间
    private Date ReleaseTime;
    //上映的电影院
    private List<CinemaModel> cinemas;
    //出售价格
    private BigDecimal price;

    public String getMovieSn() {
        return movieSn;
    }

    public void setMovieSn(String movieSn) {
        this.movieSn = movieSn;
    }

    public Date getReleaseTime() {
        return ReleaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        ReleaseTime = releaseTime;
    }

    public List<CinemaModel> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<CinemaModel> cinemas) {
        this.cinemas = cinemas;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
