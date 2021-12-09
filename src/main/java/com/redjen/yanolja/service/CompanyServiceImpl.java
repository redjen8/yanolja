package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.CompanyMapper;
import com.redjen.yanolja.model.vo.CompanyVO;
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
    public List<CompanyVO> searchCompanyByReserve(int listSize, int pageNumber) {
        return companyMapper.searchCompanyByReserve(listSize, pageNumber * listSize);
    }

    @Override
    public List<CompanyVO> searchCompanyByLikes(int listSize, int pageNumber) {
        return companyMapper.searchCompanyByLikes(listSize, pageNumber * listSize);
    }

    @Override
    public List<CompanyVO> searchCompanyByLocation(String searchCondition) {
        return companyMapper.searchCompanyByLocation(searchCondition);
    }
}
