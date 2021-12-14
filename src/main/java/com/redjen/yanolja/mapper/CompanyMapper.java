package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.dto.CompanyQueryResultDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyMapper {

    List<CompanyQueryResultDTO> searchCompanyByReserve(int listSize, int offset);

    List<CompanyQueryResultDTO> searchCompanyByLikes(int listSize, int offset);

    List<CompanyQueryResultDTO> searchCompanyByLocation(String searchCondition);
}

