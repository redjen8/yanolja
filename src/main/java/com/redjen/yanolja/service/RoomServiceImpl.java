package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.mapper.RoomMapper;
import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.model.Room;
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
    public List<Room> searchAvailableRoomList(String conditionStart, String conditionEnd) {
        return roomMapper.searchAvailableRoomList(conditionStart, conditionEnd);
    }
}
