package com.redjen.yanolja.controller;

import com.redjen.yanolja.configuration.BaseException;
import com.redjen.yanolja.model.dto.LoginDTO;
import com.redjen.yanolja.model.vo.MemberVO;
import com.redjen.yanolja.security.AES128;
import com.redjen.yanolja.security.JwtService;
import com.redjen.yanolja.security.Secret;
import com.redjen.yanolja.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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

        MemberVO memberVO = memberService.searchMemberByIdx(idx);
        Map<String, Object> resultMap = new HashMap<>();

        if(memberVO == null) {
            resultMap.put("resultCode", -1);
            resultMap.put("resultMsg", "해당 인덱스의 사용자가 존재하지 않습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        resultMap.put("memberIdx", memberVO.getMemberIdx());
        resultMap.put("email", memberVO.getEmail());
        resultMap.put("phoneNumber", memberVO.getPhoneNumber());
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
    @ApiOperation(value="회원가입", notes="신규 사용자가 회원 가입한다.")
    public ResponseEntity<Map<String, Object>> signupMember(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> resultMap = new HashMap<>();

        if (loginDTO.getEmail() == null || loginDTO.getPassword() == null) {
            resultMap.put("resultCode", 1);
            resultMap.put("resultMsg", "유효하지 않은 파라미터입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        MemberVO existMemberVO = memberService.searchMemberByEmail(loginDTO.getEmail());
        if(existMemberVO != null) {
            resultMap.put("resultCode", 2);
            resultMap.put("resultMsg", "이미 존재하는 이메일입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        String encodedPassword;
        try {
            encodedPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).encrypt(loginDTO.getPassword());
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 3);
            resultMap.put("resultMsg", "비밀번호 암호화 도중 에러가 발생했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        int res = memberService.signupMember(loginDTO.getEmail(), encodedPassword);
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
    @ApiOperation(value="로그인", notes="기존 사용자가 로그인해 jwt 값을 얻는다.")
    public ResponseEntity<Map<String, Object>> loginMember(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> resultMap = new HashMap<>();

        MemberVO existMemberVO = memberService.searchMemberByEmail(loginDTO.getEmail());

        String decodedPassword;
        try {
            decodedPassword = new AES128(Secret.USER_INFO_PASSWORD_KEY).decrypt(existMemberVO.getPassword());
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 3);
            resultMap.put("resultMsg", "비밀번호 복호화 도중 에러가 발생했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        if (loginDTO.getPassword().equals(decodedPassword)) {
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "로그인 성공");

            int memberIdx = existMemberVO.getMemberIdx();

            resultMap.put("memberIdx", memberIdx);

            String jwt = jwtService.createJwt(memberIdx);
            resultMap.put("jwtKey", jwt);
        }
        else {
            resultMap.put("resultCode", 1);
            resultMap.put("resultMsg", "로그인 실패");
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @ResponseBody
    @PatchMapping("/update/{memberIdx}")
    @ApiOperation(value="사용자 정보 업데이트", notes="해당 인덱스의 사용자의 정보를 업데이트한다.")
    public ResponseEntity<Map<String, Object>> updateMember (@PathVariable int memberIdx, @RequestBody MemberVO memberVO) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            int memberIdxByJwt = jwtService.getUserIdx();
            if (memberIdxByJwt != memberIdx) {
                resultMap.put("resultCode", 4);
                resultMap.put("resultMsg", "jwt 인증 오류");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }
        catch (BaseException exception) {
            resultMap.put("resultCode", 3);
            resultMap.put("resultMsg", "jwt 검증 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        int res;

        memberVO.setMemberIdx(memberIdx);
        try {
            res = memberService.updateMember(memberVO);
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 2);
            resultMap.put("resultMsg", "사용자 정보를 불러오는 도중 에러가 발생했습니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        if(res == 1) {
            resultMap.put("resultCode", 0);
            resultMap.put("resultMsg", "사용자 정보 업데이트 성공");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        else {
            resultMap.put("resultCode", 1);
            resultMap.put("resultMsg", "사용자 정보 업데이트 실패");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
    }

    @Transactional
    @ResponseBody
    @PostMapping("/like/company/{companyIdx}")
    @ApiOperation(value="사용자 숙소 좋아요 요청", notes="사용자 별 숙소 좋아요 요청 처리, 동일한 요청 재전송 시 좋아요 취소")
    public ResponseEntity<Map<String, Object>> makeLikeToCompany(@PathVariable int companyIdx, @RequestParam int memberIdx) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            int memberIdxByJwt = jwtService.getUserIdx();
            if (memberIdxByJwt != memberIdx) {
                resultMap.put("resultCode", 4);
                resultMap.put("resultMsg", "jwt 인증 오류");
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        }
        catch (BaseException exception) {
            resultMap.put("resultCode", 3);
            resultMap.put("resultMsg", "jwt 검증 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }

        try {
            int checkAlreadyLikedCompany = memberService.checkAlreadyLikedCompany(memberIdx, companyIdx);
            if(checkAlreadyLikedCompany == 1) {  // 이미 클릭했던 경우
                int cancelRes = memberService.cancelLikeToCompany(memberIdx, companyIdx);
                if (cancelRes == 1) {
                    resultMap.put("resultCode", 0);
                    resultMap.put("resultMsg", "좋아요 취소 완료");
                }
                else {
                    resultMap.put("resultCode", 5);
                    resultMap.put("resultMsg", "좋아요 취소 실패");
                }
            }
            else {
                int makeRes = memberService.makeLikeToCompany(memberIdx, companyIdx);
                if (makeRes == 1) {
                    resultMap.put("resultCode", 0);
                    resultMap.put("resultMsg", "좋아요 등록 완료");
                }
                else {
                    resultMap.put("resultCode", 5);
                    resultMap.put("resultMsg", "좋아요 등록 실패");
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            resultMap.put("resultCode", 2);
            resultMap.put("resultMsg", "데이터베이스 접근 오류");
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        }
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
