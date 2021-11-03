package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.ReservationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private ReservationMapper reservationMapper;

    @Autowired
    ReservationServiceImpl (ReservationMapper reservationMapper) { this.reservationMapper=reservationMapper; }

    @Override
    public int makeReservation(int memberIdx, int couponIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd) {
        return reservationMapper.makeReservation(memberIdx, couponIdx, companyIdx, roomIdx, reserveType, reserveStart, reserveEnd);
    }
}
