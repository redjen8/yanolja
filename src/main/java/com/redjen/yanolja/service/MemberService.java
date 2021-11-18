package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.model.Member;

public interface MemberService {

    Member searchMemberByIdx(int memberIdx);

    Member searchMemberByEmail(String email);

    int signupMember(String email, String password);

    int updateMember(Member member);

    int quitMemberByIdx(int memberIdx);

    int makeLikeToCompany(int memberIdx, int companyIdx);

    int cancelLikeToCompany(int memberIdx, int companyIdx);

    int checkAlreadyLikedCompany(int memberIdx, int companyIdx);
}
