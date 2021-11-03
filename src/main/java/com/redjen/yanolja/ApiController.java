package com.redjen.yanolja;

import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.service.MemberService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/member")
    public ResponseEntity<Map<String, Object>> getMemberByIdx(@RequestParam HashMap<String, String> paramMap) {
        int idx = Integer.parseInt(paramMap.get("memberIdx"));

        Member member = memberService.searchMemberByIdx(idx);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("memberIdx", member.getMemberIdx());
        resultMap.put("email", member.getEmail());
        resultMap.put("nickname", member.getNickname());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
