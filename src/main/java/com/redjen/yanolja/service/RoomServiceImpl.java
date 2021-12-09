package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.RoomMapper;
import com.redjen.yanolja.model.vo.RoomVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService{
    private RoomMapper roomMapper;

    @Autowired
    public RoomServiceImpl(RoomMapper roomMapper) {
        this.roomMapper = roomMapper;
    }

    @Override
    public List<RoomVO> searchAvailableRoomList(String conditionStart, String conditionEnd) {
        return roomMapper.searchAvailableRoomList(conditionStart, conditionEnd);
    }

    @Override
    public List<RoomVO> searchCouponAvailableRoomList(String conditionStart, String conditionEnd, int couponIdx) {
        return roomMapper.searchCouponAvailRoomList(conditionStart, conditionEnd, couponIdx);
    }
}
