package com.redjen.yanolja.service;

import com.redjen.yanolja.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberService {
    Member selectEveryMember();
}
