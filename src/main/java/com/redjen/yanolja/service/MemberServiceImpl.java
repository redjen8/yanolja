package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.model.Member;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService {

    MemberMapper memberMapper;

    @Override
    public Member selectEveryMember() {
        return memberMapper.searchEveryMember();
    }
}
