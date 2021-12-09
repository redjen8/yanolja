package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.vo.CouponVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CouponMapper {

    int registerNewCoupon (CouponVO couponVO);

    CouponVO selectCouponByCouponIdx (int couponIdx);

    List<CouponVO> selectAvailableCouponList(int roomIdx);

    int registerCouponAvailable(int roomIdx, int couponIdx);
}

