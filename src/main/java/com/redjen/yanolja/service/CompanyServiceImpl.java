package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.CompanyMapper;
import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.model.Company;
import com.redjen.yanolja.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }


    @Override
    public List<Company> searchCompanyByReserve(int listSize) {
        return companyMapper.searchCompanyByReserve(listSize);
    }

    @Override
    public List<Company> searchCompanyByLikes(int listSize) {
        return companyMapper.searchCompanyByLikes(listSize);
    }
}
