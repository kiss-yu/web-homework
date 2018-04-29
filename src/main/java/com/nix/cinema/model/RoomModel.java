package com.nix.cinema.model;

import com.nix.cinema.model.base.BaseModel;

/**
 * @author Kiss
 * @date 2018/04/29 20:44
 * 房间
 */
public class RoomModel extends BaseModel<RoomModel> {
    private String roomSn;
    //房间容量
    private Integer capacity;
    //房间号
    private Integer roomId;

    public String getRoomSn() {
        return roomSn;
    }

    public void setRoomSn(String roomSn) {
        this.roomSn = roomSn;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
