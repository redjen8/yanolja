package com.redjen.yanolja.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ReservationMapper {
    int makeReservationWithCoupon(int memberIdx, int couponIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd);

    int makeReservation(int memberIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd);
}

