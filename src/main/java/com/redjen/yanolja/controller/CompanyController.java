package com.redjen.yanolja.controller;

import com.redjen.yanolja.model.Company;
import com.redjen.yanolja.model.CompanyOwner;
import com.redjen.yanolja.service.CompanyOwnerService;
import com.redjen.yanolja.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyOwnerService companyOwnerService;

    @GetMapping("/company/list/by-reserve")
    @ApiOperation(value="예약 내림차 순 카테고리 별 숙소 조회", notes="예약 수의 내림차순으로 숙소 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyByReserve(@RequestParam int listSize) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByReserve(listSize);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/company/list/by-likes")
    @ApiOperation(value="좋아요 내림차 순 카테고리 별 숙소 조회", notes="좋아요 수의 내림차순으로 숙소 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyByLikes(@RequestParam int listSize) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByLikes(listSize);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/company/list/by-location")
    @ApiOperation(value="지역 별 숙소 조회", notes="도/ 시,군,구 정보와 일치하는 숙소 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyByLocation(@RequestParam String searchCondition) {
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByLocation(searchCondition);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/owner/list")
    @ApiOperation(value="사업자 목록 조회", notes="숙소 사업자 대표의 정보를 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyOwner(@RequestParam int companyIdx) {
        Map<String, Object> resultMap = new HashMap<>();
        List<CompanyOwner> companyOwnerList = companyOwnerService.searchCompanyOwner(companyIdx);
        resultMap.put("data", companyOwnerList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
