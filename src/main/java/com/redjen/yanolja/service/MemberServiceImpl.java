package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService{
    private MemberMapper memberMapper;

    @Autowired
    public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member searchMemberByIdx(int memberIdx) {
        return memberMapper.searchMemberByIdx(memberIdx);
    }
}