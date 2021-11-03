package com.redjen.yanolja.service;

import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.model.Room;

import java.util.List;

public interface RoomService {

    List<Room> searchAvailableRoomList(String conditionStart, String conditionEnd);
    List<Room> searchCouponAvailableRoomList(String conditionStart, String conditionEnd, int couponIdx);
}
