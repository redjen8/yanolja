package com.redjen.yanolja.service;

import com.redjen.yanolja.model.vo.RoomVO;

import java.util.List;

public interface RoomService {

    List<RoomVO> searchAvailableRoomList(String conditionStart, String conditionEnd);
    List<RoomVO> searchCouponAvailableRoomList(String conditionStart, String conditionEnd, int couponIdx);
}
