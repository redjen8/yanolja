package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.CompanyMapper;
import com.redjen.yanolja.model.dto.CompanyQueryResultDTO;
import com.redjen.yanolja.model.vo.CompanyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyMapper companyMapper;

    @Autowired
    public CompanyServiceImpl(CompanyMapper companyMapper) {
        this.companyMapper = companyMapper;
    }


    @Override
    public List<CompanyQueryResultDTO> searchCompanyByReserve(int listSize, int pageNumber) {
        return companyMapper.searchCompanyByReserve(listSize, pageNumber * listSize);
    }

    @Override
    public List<CompanyQueryResultDTO> searchCompanyByLikes(int listSize, int pageNumber) {
        return companyMapper.searchCompanyByLikes(listSize, pageNumber * listSize);
    }

    @Override
    public List<CompanyQueryResultDTO> searchCompanyByLocation(String searchCondition) {
        return companyMapper.searchCompanyByLocation(searchCondition);
    }
}
