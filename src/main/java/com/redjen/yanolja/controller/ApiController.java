package com.redjen.yanolja.controller;

import com.redjen.yanolja.model.*;
import com.redjen.yanolja.service.*;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyOwnerService companyOwnerService;

    @GetMapping("/member/get")
    @ApiOperation(value="사용자 정보 조회", notes="해당 인덱스의 사용자 정보를 조회한다.")
    public ResponseEntity<Map<String, Object>> getMemberByIdx(@RequestParam HashMap<String, String> paramMap) {
        int idx = Integer.parseInt(paramMap.get("memberIdx"));

        Member member = memberService.searchMemberByIdx(idx);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("memberIdx", member.getMemberIdx());
        resultMap.put("email", member.getEmail());
        resultMap.put("phoneNumber", member.getPhoneNumber());
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/room/search/available")
    @ApiOperation(value="예약 가능한 숙소 조회", notes="전체 방 중 현재 예약이 가능한 방 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchAvailableRoomList(@RequestParam HashMap<String, String> paramMap) {
        String conditionStart = paramMap.get("conditionStart");
        String conditionEnd = paramMap.get("conditionEnd");

        List<Room> availRoomList = roomService.searchAvailableRoomList(conditionStart, conditionEnd);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", availRoomList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/room/search/coupon-available")
    @ApiOperation(value="쿠폰 사용 가능하고 예약 가능한 숙소 조회", notes="쿠폰 사용 가능한 방 중 현재 예약이 가능한 방 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCouponAvailableRoomList(@RequestParam HashMap<String, String> paramMap) {
        String conditionStart = paramMap.get("conditionStart");
        String conditionEnd = paramMap.get("conditionEnd");
        int couponIdx = Integer.parseInt(paramMap.get("couponIdx"));

        List<Room> availRoomList = roomService.searchCouponAvailableRoomList(conditionStart, conditionEnd, couponIdx);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", availRoomList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/reserve/make")
    @ApiOperation(value="방 예약", notes="방 예약 정보를 기록한다.")
    public ResponseEntity<Map<String, Object>> makeReservation(@RequestBody HashMap<String, String> paramMap) {
        int memberIdx = Integer.parseInt(paramMap.get("memberIdx"));
        int couponIdx = Integer.parseInt(paramMap.get("couponIdx"));
        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        int roomIdx = Integer.parseInt(paramMap.get("roomIdx"));
        boolean reserveType = paramMap.get("reserveType").equals("1");
        String reserveStart = paramMap.get("reserveStart");
        String reserveEnd = paramMap.get("reserveEnd");
        int result = reservationService.makeReservation(memberIdx, couponIdx, companyIdx,roomIdx,reserveType,reserveStart,reserveEnd);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("resultCode", result);
        resultMap.put("resultMsg", "정상적으로 예약되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/review/write")
    @ApiOperation(value="후기 작성", notes="실 사용 후 예약에 대한 후기를 작성한다.")
    public ResponseEntity<Map<String, Object>> writeNewReview(@RequestBody HashMap<String, String> paramMap) {
        int memberIdx = Integer.parseInt(paramMap.get("memberIdx"));
        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        int roomIdx = Integer.parseInt(paramMap.get("roomIdx"));
        int reserveIdx = Integer.parseInt(paramMap.get("reserveIdx"));
        float rating = Float.parseFloat(paramMap.get("rating"));
        String reviewDescription = paramMap.get("reviewDescription");
        Map<String, Object> resultMap = new HashMap<>();
        int result = reviewService.insertNewReview(memberIdx, companyIdx, roomIdx, reserveIdx, rating, reviewDescription);
        resultMap.put("resultCode", result);
        resultMap.put("resultMsg", "정상적으로 예약되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/review/reply")
    @ApiOperation(value="후기 답변 작성", notes="후기에 대한 답변을 작성한다.")
    public ResponseEntity<Map<String, Object>> writeReplyToReview(@RequestBody HashMap<String, String> paramMap) {
        int reviewIdx = Integer.parseInt(paramMap.get("reviewIdx"));
        String reviewReply = paramMap.get("reviewReply");
        Map<String, Object> resultMap = new HashMap<>();
        int result = reviewService.insertReplyToReview(reviewIdx, reviewReply);
        resultMap.put("resultCode", result);
        resultMap.put("resultMsg", "정상적으로 예약되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/review/search")
    @ApiOperation(value="숙소 리뷰 목록 조회", notes="숙소의 모든 리뷰 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchReviewList(@RequestParam HashMap<String, String> paramMap) {
        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        Map<String, Object> resultMap = new HashMap<>();
        List<Review> reviewList = reviewService.searchReviewList(companyIdx);
        resultMap.put("data", reviewList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/company/list/by-reserve")
    @ApiOperation(value="예약 내림차 순 카테고리 별 숙소 조회", notes="예약 수의 내림차순으로 숙소 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyByReserve(@RequestParam HashMap<String, String> paramMap) {
        int listSize = Integer.parseInt(paramMap.get("listSize"));
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByReserve(listSize);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/company/list/by-likes")
    @ApiOperation(value="좋아요 내림차 순 카테고리 별 숙소 조회", notes="좋아요 수의 내림차순으로 숙소 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyByLikes(@RequestParam HashMap<String, String> paramMap) {
        int listSize = Integer.parseInt(paramMap.get("listSize"));
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByLikes(listSize);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/company/list/by-location")
    @ApiOperation(value="지역 별 숙소 조회", notes="도/ 시,군,구 정보와 일치하는 숙소 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyByLocation(@RequestParam HashMap<String, String> paramMap) {
        String searchCondition = paramMap.get("searchCondition");
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByLocation(searchCondition);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/owner/list")
    @ApiOperation(value="사업자 목록 조회", notes="숙소 사업자 대표의 정보를 조회한다.")
    public ResponseEntity<Map<String, Object>> searchCompanyOwner(@RequestParam HashMap<String, String> paramMap) {
        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        Map<String, Object> resultMap = new HashMap<>();
        List<CompanyOwner> companyOwnerList = companyOwnerService.searchCompanyOwner(companyIdx);
        resultMap.put("data", companyOwnerList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
