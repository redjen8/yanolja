package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.CompanyOwnerMapper;
import com.redjen.yanolja.model.CompanyOwner;
import com.redjen.yanolja.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyOwnerServiceImpl implements CompanyOwnerService{
    private CompanyOwnerMapper companyOwnerMapper;

    @Autowired
    public CompanyOwnerServiceImpl(CompanyOwnerMapper companyOwnerMapper) {
        this.companyOwnerMapper = companyOwnerMapper;
    }

    @Override
    public List<CompanyOwner> searchCompanyOwner(int companyIdx) {
        return companyOwnerMapper.searchCompanyOwner(companyIdx);
    }
}
