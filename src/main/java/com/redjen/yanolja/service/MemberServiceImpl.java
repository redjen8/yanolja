package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.model.vo.MemberVO;
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
    public MemberVO searchMemberByIdx(int memberIdx) {
        return memberMapper.searchMemberByIdx(memberIdx);
    }

    @Override
    public MemberVO searchMemberByEmail(String email) {
        return memberMapper.searchMemberByEmail(email);
    }

    @Override
    public int signupMember(String email, String password) {
        return memberMapper.signupMember(email, password);
    }

    @Override
    public int updateMember(MemberVO memberVO) {
        return memberMapper.updateMember(memberVO);
    }

    @Override
    public int quitMemberByIdx(int memberIdx) {
        return memberMapper.quitMemberByIdx(memberIdx);
    }

    @Override
    public int makeLikeToCompany(int memberIdx, int companyIdx) {
        return memberMapper.makeLikeToCompany(memberIdx, companyIdx);
    }

    @Override
    public int cancelLikeToCompany(int memberIdx, int companyIdx) {
        return memberMapper.cancelLikeToCompany(memberIdx, companyIdx);
    }

    @Override
    public int checkAlreadyLikedCompany(int memberIdx, int companyIdx) {
        return memberMapper.checkAlreadyLikedCompany(memberIdx, companyIdx);
    }

}
