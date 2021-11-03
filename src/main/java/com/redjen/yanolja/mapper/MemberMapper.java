package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface MemberMapper {
//    Member searchMemberByIdx(int memberIdx);

    Member searchMemberByIdx(int memberIdx);
}

