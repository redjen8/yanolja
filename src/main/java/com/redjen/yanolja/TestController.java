package com.redjen.yanolja;

import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.service.MemberService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Setter(onMethod_ = {@Autowired})
    MemberService memberService;

    @GetMapping("/test")
    public String test() {
        Member myMember = memberService.selectEveryMember();
        return myMember.toString();
    }
}
