package com.redjen.yanolja.service;

import com.redjen.yanolja.model.Coupon;

import java.util.List;

public interface CouponService {
    int registerNewCoupon (Coupon coupon);

    Coupon selectCouponByCouponIdx (int couponIdx);

    List<Coupon> selectAvailableCouponList(int roomIdx);

    int registerCouponAvailable(int roomIdx, int couponIdx);
}
