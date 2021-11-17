package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.CouponMapper;
import com.redjen.yanolja.model.Coupon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponServiceImpl implements CouponService{
    private CouponMapper couponMapper;

    @Autowired
    public CouponServiceImpl(CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
    }

    @Override
    public int registerNewCoupon(Coupon coupon) {
        return couponMapper.registerNewCoupon(coupon);
    }

    @Override
    public Coupon selectCouponByCouponIdx(int couponIdx) {
        return couponMapper.selectCouponByCouponIdx(couponIdx);
    }

    @Override
    public List<Coupon> selectAvailableCouponList(int roomIdx) {
        return couponMapper.selectAvailableCouponList(roomIdx);
    }

    @Override
    public int registerCouponAvailable(int roomIdx, int couponIdx) {
        return couponMapper.registerCouponAvailable(roomIdx, couponIdx);
    }
}
