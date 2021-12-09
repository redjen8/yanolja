package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

    MemberVO searchMemberByIdx(int memberIdx);

    MemberVO searchMemberByEmail(String email);

    int signupMember(String email, String password);

    int updateMember(MemberVO memberVO);

    int quitMemberByIdx(int memberIdx);

    int makeLikeToCompany(int memberIdx, int companyIdx);

    int cancelLikeToCompany(int memberIdx, int companyIdx);

    int checkAlreadyLikedCompany(int memberIdx, int companyIdx);
}

