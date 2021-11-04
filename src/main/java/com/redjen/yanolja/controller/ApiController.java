package com.redjen.yanolja.controller;

import com.redjen.yanolja.mapper.CompanyMapper;
import com.redjen.yanolja.mapper.ReservationMapper;
import com.redjen.yanolja.mapper.ReviewMapper;
import com.redjen.yanolja.model.*;
import com.redjen.yanolja.service.*;
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
    public ResponseEntity<Map<String, Object>> searchAvailableRoomList(@RequestParam HashMap<String, String> paramMap) {
        String conditionStart = paramMap.get("conditionStart");
        String conditionEnd = paramMap.get("conditionEnd");

        List<Room> availRoomList = roomService.searchAvailableRoomList(conditionStart, conditionEnd);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("data", availRoomList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/room/search/coupon-available")
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
    public ResponseEntity<Map<String, Object>> makeReservation(@RequestParam HashMap<String, String> paramMap) {
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
    public ResponseEntity<Map<String, Object>> writeNewReview(@RequestParam HashMap<String, String> paramMap) {
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
    public ResponseEntity<Map<String, Object>> writeReplyToReview(@RequestParam HashMap<String, String> paramMap) {
        int reviewIdx = Integer.parseInt(paramMap.get("reviewIdx"));
        String reviewReply = paramMap.get("reviewReply");
        Map<String, Object> resultMap = new HashMap<>();
        int result = reviewService.insertReplyToReview(reviewIdx, reviewReply);
        resultMap.put("resultCode", result);
        resultMap.put("resultMsg", "정상적으로 예약되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/review/search")
    public ResponseEntity<Map<String, Object>> searchReviewList(@RequestParam HashMap<String, String> paramMap) {
        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        Map<String, Object> resultMap = new HashMap<>();
        List<Review> reviewList = reviewService.searchReviewList(companyIdx);
        resultMap.put("data", reviewList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/company/list/order-by/reserve")
    public ResponseEntity<Map<String, Object>> searchCompanyByReserve(@RequestParam HashMap<String, String> paramMap) {
        int listSize = Integer.parseInt(paramMap.get("listSize"));
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByReserve(listSize);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/company/list/order-by/likes")
    public ResponseEntity<Map<String, Object>> searchCompanyByLikes(@RequestParam HashMap<String, String> paramMap) {
        int listSize = Integer.parseInt(paramMap.get("listSize"));
        Map<String, Object> resultMap = new HashMap<>();
        List<Company> companyList = companyService.searchCompanyByLikes(listSize);
        resultMap.put("data", companyList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/owner/list")
    public ResponseEntity<Map<String, Object>> searchCompanyOwner(@RequestParam HashMap<String, String> paramMap) {
        int companyIdx = Integer.parseInt(paramMap.get("companyIdx"));
        Map<String, Object> resultMap = new HashMap<>();
        List<CompanyOwner> companyOwnerList = companyOwnerService.searchCompanyOwner(companyIdx);
        resultMap.put("data", companyOwnerList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
