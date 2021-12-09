package com.redjen.yanolja.service;

import com.redjen.yanolja.model.vo.CompanyVO;

import java.util.List;

public interface CompanyService {

    List<CompanyVO> searchCompanyByReserve(int listSize, int pageNum);

    List<CompanyVO> searchCompanyByLikes(int listSize, int pageNum);

    List<CompanyVO> searchCompanyByLocation(String searchCondition);
}
