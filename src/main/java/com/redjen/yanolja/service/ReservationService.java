package com.redjen.yanolja.service;

public interface ReservationService {

    int makeReservation(int memberIdx, int couponIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd);
}
