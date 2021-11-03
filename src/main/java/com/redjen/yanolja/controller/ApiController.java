package com.redjen.yanolja.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.redjen.yanolja.mapper.ReservationMapper;
import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.model.Room;
import com.redjen.yanolja.service.MemberService;
import com.redjen.yanolja.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationMapper reservationMapper;

    @GetMapping("/member/get")
    public ResponseEntity<Map<String, Object>> getMemberByIdx(@RequestParam HashMap<String, String> paramMap) {
        int idx = Integer.parseInt(paramMap.get("memberIdx"));

        Member member = memberService.searchMemberByIdx(idx);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("memberIdx", member.getMemberIdx());
        resultMap.put("email", member.getEmail());
        resultMap.put("phoneNumber", member.getPhoneNumber());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/room/search/available")
    public ResponseEntity<Map<String, Object>> searchAvailableRoomList(@RequestParam HashMap<String, String> paramMap) {
        String conditionStart = paramMap.get("conditionStart");
        String conditionEnd = paramMap.get("conditionEnd");

        List<Room> availRoomList = roomService.searchAvailableRoomList(conditionStart, conditionEnd);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", availRoomList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/room/search/coupon-available")
    public ResponseEntity<Map<String, Object>> searchCouponAvailableRoomList(@RequestParam HashMap<String, String> paramMap) {
        String conditionStart = paramMap.get("conditionStart");
        String conditionEnd = paramMap.get("conditionEnd");
        int couponIdx = Integer.parseInt(paramMap.get("couponIdx"));

        List<Room> availRoomList = roomService.searchCouponAvailableRoomList(conditionStart, conditionEnd, couponIdx);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", availRoomList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/reserve/make")
    public ResponseEntity<Map<String, Object>> makeReservation(@RequestParam HashMap<String, String> paramMap) {
        int memberIdx = Integer.parseInt(paramMap.get("memberIdx"));
        int couponIdx = Integer.parseInt(paramMap.get("couponIdx"));
        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        int roomIdx = Integer.parseInt(paramMap.get("roomIdx"));
        boolean reserveType = paramMap.get("reserveType").equals("1");
        String reserveStart = paramMap.get("reserveStart");
        String reserveEnd = paramMap.get("reserveEnd");
        int result = reservationMapper.makeReservation(memberIdx, couponIdx, companyIdx,roomIdx,reserveType,reserveStart,reserveEnd);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCode", result);
        resultMap.put("resultMsg", "정상적으로 예약되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
