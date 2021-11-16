package com.redjen.yanolja.controller;

import com.redjen.yanolja.model.*;
import com.redjen.yanolja.security.AES128;
import com.redjen.yanolja.security.JwtService;
import com.redjen.yanolja.security.Secret;
import com.redjen.yanolja.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/get/{idx}")
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

    @PatchMapping("/quit/{idx}")
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

    @ResponseBody
    @PostMapping(value = "/signup")
    public ResponseEntity<Map<String, Object>> signupMember(@RequestBody LoginVO loginVO) {
        Map<String, Object> resultMap = new HashMap<>();

        if (loginVO.getEmail() == null || loginVO.getPassword() == null) {
            resultMap.put("resultCode", 1);
            resultMap.put("resultMsg", "유효하지 않은 파라미터입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        Member existMember = memberService.searchMemberByEmail(loginVO.getEmail());
        if(existMember != null) {
            resultMap.put("resultCode", 2);
            resultMap.put("resultMsg", "이미 존재하는 이메일입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        String encodedPassword;
        try {
            encodedPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(loginVO.getPassword());
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 3);
            resultMap.put("resultMsg", "비밀번호 암호화 도중 에러가 발생했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        int res = memberService.signupMember(loginVO.getEmail(), encodedPassword);
        if (res == 1) {
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "회원가입 성공");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        else {
            resultMap.put("resultCode", 4);
            resultMap.put("resultMsg", "데이터베이스 삽입 도중 에러가 발생했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> loginMember(@RequestBody LoginVO loginVO) {
        Map<String, Object> resultMap = new HashMap<>();

        Member existMember = memberService.searchMemberByEmail(loginVO.getEmail());

        String decodedPassword;
        try {
            decodedPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(existMember.getPassword());
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 3);
            resultMap.put("resultMsg", "비밀번호 복호화 도중 에러가 발생했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        if (loginVO.getPassword().equals(decodedPassword)) {
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "로그인 성공");

            int memberIdx = existMember.getMemberIdx();

            resultMap.put("memberIdx", memberIdx);

            String jwt = jwtService.createJwt(memberIdx);
            resultMap.put("jwtKey", jwt);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        else {
            resultMap.put("resultCode", 1);
            resultMap.put("resultMsg", "로그인 실패");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }
}
