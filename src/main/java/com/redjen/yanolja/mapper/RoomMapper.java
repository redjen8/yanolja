package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.vo.RoomVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoomMapper {

    List<RoomVO> searchAvailableRoomList(String conditionStart, String conditionEnd);
    List<RoomVO> searchCouponAvailRoomList(String conditionStart, String conditionEnd, int couponIdx);
}

