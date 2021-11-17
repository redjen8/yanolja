package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.Company;
import com.redjen.yanolja.model.Coupon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CouponMapper {

    int registerNewCoupon (Coupon coupon);

    Coupon selectCouponByCouponIdx (int couponIdx);

    List<Coupon> selectAvailableCouponList(int roomIdx);

    int registerCouponAvailable(int roomIdx, int couponIdx);
}

