package com.redjen.yanolja.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redjen.yanolja.model.Coupon;
import com.redjen.yanolja.service.CouponService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @ResponseBody
    @PostMapping("/register")
    @ApiOperation(value="새 쿠폰 등록", notes="새 쿠폰을 등록한다.")
    public ResponseEntity<Map<String, Object>> registerNewCoupon(@RequestBody Coupon coupon) {
        Map<String, Object> resultMap = new HashMap<>();

        if (coupon.getCouponName() == null || coupon.getDescription() == null || coupon.getStartTime() == null || coupon.getEndTime() == null) {
            resultMap.put("resultCode", 1);
            resultMap.put("resultMsg", "유효하지 않은 파라미터입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        int res;

        try {
            res = couponService.registerNewCoupon(coupon);
        }
        catch(Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 4);
            resultMap.put("resultMsg", "데이터베이스 접근 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        if(res == 1) {
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "새 쿠폰 등록에 성공했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        else {
            resultMap.put("resultCode", 2);
            resultMap.put("resultMsg", "쿠폰 등록에 실패했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }

    @ResponseBody
    @GetMapping("/{couponIdx}")
    @ApiOperation(value="쿠폰 정보 조회", notes="등록된 쿠폰 정보를 조회한다.")
    public ResponseEntity<Map<String, Object>> selectCouponByCouponIdx (@PathVariable int couponIdx) {
        Map<String, Object> resultMap = new HashMap<>();

        Coupon coupon;
        try {
            coupon = couponService.selectCouponByCouponIdx(couponIdx);
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 4);
            resultMap.put("resultMsg", "데이터베이스 접근 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> data = objectMapper.convertValue(coupon, Map.class);

        resultMap.put("resultCode", 0);
        resultMap.put("resultMsg", "쿠폰 정보 불러오기 성공");
        resultMap.put("data", data);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
