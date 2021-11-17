package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {

    Member searchMemberByIdx(int memberIdx);

    Member searchMemberByEmail(String email);

    int signupMember(String email, String password);

    int updateMember(Member member);

    int quitMemberByIdx(int memberIdx);
}

