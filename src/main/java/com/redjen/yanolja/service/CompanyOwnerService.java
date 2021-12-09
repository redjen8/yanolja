package com.redjen.yanolja.service;

import com.redjen.yanolja.model.vo.CompanyOwnerVO;

import java.util.List;

public interface CompanyOwnerService {

    List<CompanyOwnerVO> searchCompanyOwner(int companyIdx);
}
