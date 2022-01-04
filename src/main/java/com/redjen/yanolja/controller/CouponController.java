package com.redjen.yanolja.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.redjen.yanolja.model.vo.CouponVO;
import com.redjen.yanolja.service.CouponService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;

    CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @ResponseBody
    @PostMapping("/register")
    @ApiOperation(value="새 쿠폰 등록", notes="새 쿠폰을 등록한다.")
    public ResponseEntity<Map<String, Object>> registerNewCoupon(@RequestBody CouponVO couponVO) {
        Map<String, Object> resultMap = new HashMap<>();

        if (couponVO.getCouponName() == null || couponVO.getDescription() == null || couponVO.getStartTime() == null || couponVO.getEndTime() == null) {
            resultMap.put("resultCode", 1);
            resultMap.put("resultMsg", "유효하지 않은 파라미터입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        int res;

        try {
            res = couponService.registerNewCoupon(couponVO);
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

        CouponVO couponVO;
        try {
            couponVO = couponService.selectCouponByCouponIdx(couponIdx);
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 4);
            resultMap.put("resultMsg", "데이터베이스 접근 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        Map<String, Object> data = objectMapper.convertValue(couponVO, Map.class);

        resultMap.put("resultCode", 0);
        resultMap.put("resultMsg", "쿠폰 정보 불러오기 성공");
        resultMap.put("data", data);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping("/list")
    @ApiOperation(value="가용한 쿠폰 리스트 조회", notes="현재 방에 사용 가능한 쿠폰 목록를 조회한다.")
    public ResponseEntity<Map<String, Object>> selectAvailableCouponList (@RequestParam int roomIdx) {
        Map<String, Object> resultMap = new HashMap<>();

        List<CouponVO> availList;
        try {
            availList = couponService.selectAvailableCouponList(roomIdx);
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 4);
            resultMap.put("resultMsg", "데이터베이스 접근 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        resultMap.put("resultCode", 0);
        resultMap.put("resultMsg", "사용 가능한 쿠폰 정보 불러오기 성공");
        resultMap.put("data", availList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    @PostMapping("/register/room")
    @ApiOperation(value="쿠폰 가용 정보 등록", notes="방 마다 사용 가능한 쿠폰을 등록한다.")
    public ResponseEntity<Map<String, Object>> registerCouponAvailable (@RequestParam int roomIdx, @RequestParam("couponIdx") ArrayList<Integer> couponIdxList) {
        Map<String, Object> resultMap = new HashMap<>();

        try {
            int res = 0;
            for(int eachCouponIdx : couponIdxList) {
                res = couponService.registerCouponAvailable(roomIdx, eachCouponIdx);
            }
            if (res == 0) {
                resultMap.put("resultCode", 1);
                resultMap.put("resultMsg", "쿠폰 정보 쓰기 실패");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 4);
            resultMap.put("resultMsg", "데이터베이스 접근 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        resultMap.put("resultCode", 0);
        resultMap.put("resultMsg", "방 별 가용 쿠폰 정보 저장 성공");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
