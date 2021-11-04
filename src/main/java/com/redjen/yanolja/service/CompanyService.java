package com.redjen.yanolja.service;

import com.redjen.yanolja.model.Company;
import com.redjen.yanolja.model.Member;

import java.util.List;

public interface CompanyService {

    List<Company> searchCompanyByReserve(int listSize);

    List<Company> searchCompanyByLikes(int listSize);

    List<Company> searchCompanyByLocation(String searchCondition);
}
