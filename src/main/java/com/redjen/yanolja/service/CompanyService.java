package com.redjen.yanolja.service;

import com.redjen.yanolja.model.dto.CompanyQueryResultDTO;

import java.util.List;

public interface CompanyService {

    List<CompanyQueryResultDTO> searchCompanyByReserve(int listSize, int pageNum);

    List<CompanyQueryResultDTO> searchCompanyByLikes(int listSize, int pageNum);

    List<CompanyQueryResultDTO> searchCompanyByLocation(String searchCondition);
}
