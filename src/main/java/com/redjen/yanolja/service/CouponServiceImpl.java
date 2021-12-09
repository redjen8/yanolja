package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.CouponMapper;
import com.redjen.yanolja.model.vo.CouponVO;
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
    public int registerNewCoupon(CouponVO couponVO) {
        return couponMapper.registerNewCoupon(couponVO);
    }

    @Override
    public CouponVO selectCouponByCouponIdx(int couponIdx) {
        return couponMapper.selectCouponByCouponIdx(couponIdx);
    }

    @Override
    public List<CouponVO> selectAvailableCouponList(int roomIdx) {
        return couponMapper.selectAvailableCouponList(roomIdx);
    }

    @Override
    public int registerCouponAvailable(int roomIdx, int couponIdx) {
        return couponMapper.registerCouponAvailable(roomIdx, couponIdx);
    }
}
