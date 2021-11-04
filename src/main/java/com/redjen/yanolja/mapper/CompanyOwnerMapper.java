package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.Company;
import com.redjen.yanolja.model.CompanyOwner;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyOwnerMapper {

    List<CompanyOwner> searchCompanyOwner(int companyIdx);
}
