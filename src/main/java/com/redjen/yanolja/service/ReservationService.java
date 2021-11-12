package com.redjen.yanolja.service;

public interface ReservationService {

    int makeReservationWithCoupon(int memberIdx, int couponIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd);

    int makeReservation(int memberIdx, int companyIdx, int roomIdx, boolean reserveType, String reserveStart, String reserveEnd);
}
