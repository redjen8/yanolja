package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.model.Member;

public interface MemberService {

    Member searchMemberByIdx(int memberIdx);
}
