package com.redjen.yanolja.service;

import com.redjen.yanolja.model.vo.CouponVO;

import java.util.List;

public interface CouponService {
    int registerNewCoupon (CouponVO couponVO);

    CouponVO selectCouponByCouponIdx (int couponIdx);

    List<CouponVO> selectAvailableCouponList(int roomIdx);

    int registerCouponAvailable(int roomIdx, int couponIdx);
}
