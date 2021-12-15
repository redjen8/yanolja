package com.redjen.yanolja.controller;

import com.redjen.yanolja.model.dto.CompanyQueryResultDTO;
import com.redjen.yanolja.model.vo.CompanyVO;
import com.redjen.yanolja.model.vo.CompanyOwnerVO;
import com.redjen.yanolja.service.CompanyOwnerService;
import com.redjen.yanolja.service.CompanyService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyOwnerService companyOwnerService;

    @GetMapping("/list/by-reserve")
    @ApiOperation(value="예약 내림차 순 카테고리 별 숙소 조회", notes="예약 수의 내림차순으로 숙소 목록을 조회한다.")
    public ResponseEntity<List<CompanyQueryResultDTO>> searchCompanyByReserve(@RequestParam int listSize, @RequestParam int pageNum) {
        List<CompanyQueryResultDTO> companyListByReserve = companyService.searchCompanyByReserve(listSize, pageNum);
        return new ResponseEntity<>(companyListByReserve, HttpStatus.OK);
    }

    @GetMapping("/list/by-likes")
    @ApiOperation(value="좋아요 내림차 순 카테고리 별 숙소 조회", notes="좋아요 수의 내림차순으로 숙소 목록을 조회한다.")
    public ResponseEntity<List<CompanyQueryResultDTO>> searchCompanyByLikes(@RequestParam int listSize, @RequestParam int pageNum) {
        List<CompanyQueryResultDTO> companyListByLikes = companyService.searchCompanyByLikes(listSize, pageNum);
        return new ResponseEntity<>(companyListByLikes, HttpStatus.OK);
    }

    @GetMapping("/list/by-location")
    @ApiOperation(value="지역 별 숙소 조회", notes="도/ 시,군,구 정보와 일치하는 숙소 목록을 조회한다.")
    public ResponseEntity<List<CompanyQueryResultDTO>> searchCompanyByLocation(@RequestParam String searchCondition) {
        List<CompanyQueryResultDTO> companyListByLocation = companyService.searchCompanyByLocation(searchCondition);
        return new ResponseEntity<>(companyListByLocation, HttpStatus.OK);
    }

    @GetMapping("/owner/list")
    @ApiOperation(value="사업자 목록 조회", notes="숙소 사업자 대표의 정보를 조회한다.")
    public ResponseEntity<List<CompanyOwnerVO>> searchCompanyOwner(@RequestParam int companyIdx) {
        List<CompanyOwnerVO> companyOwnerVOList = companyOwnerService.searchCompanyOwner(companyIdx);
        return new ResponseEntity<>(companyOwnerVOList, HttpStatus.OK);
    }
}
