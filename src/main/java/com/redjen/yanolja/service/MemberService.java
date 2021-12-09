package com.redjen.yanolja.service;

import com.redjen.yanolja.model.vo.MemberVO;

public interface MemberService {

    MemberVO searchMemberByIdx(int memberIdx);

    MemberVO searchMemberByEmail(String email);

    int signupMember(String email, String password);

    int updateMember(MemberVO memberVO);

    int quitMemberByIdx(int memberIdx);

    int makeLikeToCompany(int memberIdx, int companyIdx);

    int cancelLikeToCompany(int memberIdx, int companyIdx);

    int checkAlreadyLikedCompany(int memberIdx, int companyIdx);
}
