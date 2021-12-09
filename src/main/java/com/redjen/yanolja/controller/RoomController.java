package com.redjen.yanolja.controller;

import com.redjen.yanolja.model.vo.RoomVO;
import com.redjen.yanolja.service.ReservationService;
import com.redjen.yanolja.service.RoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/room/search/available")
    @ApiOperation(value="예약 가능한 숙소 조회", notes="전체 방 중 conditionStart ~ conditionEnd까지 예약이 가능한 방 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchAvailableRoomList(@RequestParam String conditionStart, @RequestParam String conditionEnd) {

        List<RoomVO> availRoomVOList = roomService.searchAvailableRoomList(conditionStart, conditionEnd);
        Map<String, Object> resultMap = new HashMap<>();

        if(availRoomVOList.size() == 0) {
            resultMap.put("resultCode", -1);
            resultMap.put("resultMsg", "조건에 해당하는 결과를 찾지 못했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        resultMap.put("data", availRoomVOList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/room/search/coupon-available")
    @ApiOperation(value="쿠폰 사용 가능하고 예약 가능한 숙소 조회", notes="couponIdx를 가지는 쿠폰을 사용 가능한 방 중 conditionStart ~ conditionEnd까지 예약이 가능한 방 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCouponAvailableRoomList(@RequestParam String conditionStart, @RequestParam String conditionEnd, @RequestParam int couponIdx) {
        List<RoomVO> availRoomVOList = roomService.searchCouponAvailableRoomList(conditionStart, conditionEnd, couponIdx);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", availRoomVOList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/reserve/make")
    @ApiOperation(value="방 예약", notes="방 예약 정보를 기록한다.")
    public ResponseEntity<Map<String, Object>> makeReservation(@RequestBody HashMap<String, String> paramMap) {
        Map<String, Object> resultMap = new HashMap<>();

        if(paramMap.isEmpty() || !paramMap.containsKey("memberIdx") || !paramMap.containsKey("companyIdx") || !paramMap.containsKey("roomIdx")
                || !paramMap.containsKey("reserveType") || !paramMap.containsKey("reserveStart") || !paramMap.containsKey("reserveEnd")) {
            resultMap.put("resultCode", -1);
            resultMap.put("resultMsg", "유효하지 않은 매개변수입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        int memberIdx = Integer.parseInt(paramMap.get("memberIdx"));
        int couponIdx;

        if(!paramMap.containsKey("couponIdx")) {
            couponIdx = 0;
        }
        else {
            couponIdx = Integer.parseInt(paramMap.get("couponIdx"));
        }

        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        int roomIdx = Integer.parseInt(paramMap.get("roomIdx"));
        boolean reserveType = paramMap.get("reserveType").equals("1");
        String reserveStart = paramMap.get("reserveStart");
        String reserveEnd = paramMap.get("reserveEnd");

        int result;

        if (couponIdx == 0) {
            result = reservationService.makeReservation(memberIdx, companyIdx, roomIdx, reserveType, reserveStart, reserveEnd);
        }
        else {
            result = reservationService.makeReservationWithCoupon(memberIdx, couponIdx, companyIdx,roomIdx,reserveType,reserveStart,reserveEnd);
        }

        if(result == 1) {
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "정상적으로 예약되었습니다.");
        }
        else {
            resultMap.put("resultCode", -1);
            resultMap.put("resultMsg", "정상적으로 예약되었습니다.");
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
