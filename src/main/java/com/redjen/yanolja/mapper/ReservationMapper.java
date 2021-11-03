package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReservationMapper {
    int makeReservation(int memberIdx, int couponIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd);
}

