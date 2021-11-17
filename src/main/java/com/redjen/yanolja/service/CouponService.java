package com.redjen.yanolja.service;

import com.redjen.yanolja.model.Coupon;

public interface CouponService {
    int registerNewCoupon (Coupon coupon);

    Coupon selectCouponByCouponIdx (int couponIdx);
}
