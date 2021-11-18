package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.Company;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyMapper {

    List<Company> searchCompanyByReserve(int listSize, int offset);

    List<Company> searchCompanyByLikes(int listSize, int offset);

    List<Company> searchCompanyByLocation(String searchCondition);
}

