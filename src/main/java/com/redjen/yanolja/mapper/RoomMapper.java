package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.model.Room;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoomMapper {

    List<Room> searchAvailableRoomList(String conditionStart, String conditionEnd);
    List<Room> searchCouponAvailRoomList(String conditionStart, String conditionEnd, int couponIdx);
    int reserveRoom(int memberIdx, int couponIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd);
}

