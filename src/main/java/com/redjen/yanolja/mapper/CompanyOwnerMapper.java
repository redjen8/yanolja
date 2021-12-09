package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.vo.CompanyOwnerVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CompanyOwnerMapper {

    List<CompanyOwnerVO> searchCompanyOwner(int companyIdx);
}

