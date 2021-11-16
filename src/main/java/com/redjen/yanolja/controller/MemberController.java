package com.redjen.yanolja.controller;

import com.redjen.yanolja.model.*;
import com.redjen.yanolja.service.*;
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
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member/get/{idx}")
    @ApiOperation(value="사용자 정보 조회", notes="해당 인덱스의 사용자 정보를 조회한다.")
    public ResponseEntity<Map<String, Object>> getMemberByIdx(@PathVariable int idx) {

        Member member = memberService.searchMemberByIdx(idx);
        Map<String, Object> resultMap = new HashMap<>();

        if(member == null) {
            resultMap.put("resultCode", -1);
            resultMap.put("resultMsg", "해당 인덱스의 사용자가 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        resultMap.put("memberIdx", member.getMemberIdx());
        resultMap.put("email", member.getEmail());
        resultMap.put("phoneNumber", member.getPhoneNumber());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PatchMapping("/member/quit/{idx}")
    @ApiOperation(value="사용자 탈퇴", notes="해당 인덱스의 사용자를 탈퇴 처리한다.")
    public ResponseEntity<Map<String, Object>> quitMemberByIdx(@PathVariable int idx) {

        int res = memberService.quitMemberByIdx(idx);
        Map<String, Object> resultMap = new HashMap<>();

        if(res == 1) {
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "해당 인덱스의 사용자 정보 삭제를 완료했습니다.");
        }
        else {
            resultMap.put("resultCode", -1);
            resultMap.put("resultMsg", "해당 인덱스의 사용자 정보 삭제에 실패했습니다.");
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
