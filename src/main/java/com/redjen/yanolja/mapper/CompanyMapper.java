package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.vo.CompanyVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyMapper {

    List<CompanyVO> searchCompanyByReserve(int listSize, int offset);

    List<CompanyVO> searchCompanyByLikes(int listSize, int offset);

    List<CompanyVO> searchCompanyByLocation(String searchCondition);
}

