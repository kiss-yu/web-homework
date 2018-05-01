package com.nix.cinema.model;

import com.nix.cinema.model.base.BaseModel;

import java.util.List;

/**
 * @author Kiss
 * @date 2018/04/29 20:44
 * 电影院
 */
public class CinemaModel extends BaseModel<CinemaModel> {
    private String cinemaSn;
    //影院所在地址（模拟）
    private String address;
    //影院介绍（html文本）
    private String introduce;
    //影院电影放映房间
    private List<RoomModel> rooms;
    //热度
    private Integer hotNumber;

    public String getCinemaSn() {
        return cinemaSn;
    }

    public void setCinemaSn(String cinemaSn) {
        this.cinemaSn = cinemaSn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public List<RoomModel> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomModel> rooms) {
        this.rooms = rooms;
    }

    public Integer getHotNumber() {
        return hotNumber;
    }

    public void setHotNumber(Integer hotNumber) {
        this.hotNumber = hotNumber;
    }
}
