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
    private Date releaseTime;
    //上映的电影院
    private List<CinemaModel> cinemas;
    //出售价格
    private BigDecimal price;
    //点击量
    private Integer hitCount;
    //简介(html文本)
    private String introduction;
    //缩略图路径
    private String img;
    //电影版权所属用户
    private UserModel user;

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public String getMovieSn() {
        return movieSn;
    }

    public void setMovieSn(String movieSn) {
        this.movieSn = movieSn;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Integer getHitCount() {
        return hitCount;
    }

    public void setHitCount(Integer hitCount) {
        this.hitCount = hitCount;
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
