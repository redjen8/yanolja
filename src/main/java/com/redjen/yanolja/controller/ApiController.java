package com.redjen.yanolja.controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.model.Room;
import com.redjen.yanolja.service.MemberService;
import com.redjen.yanolja.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
