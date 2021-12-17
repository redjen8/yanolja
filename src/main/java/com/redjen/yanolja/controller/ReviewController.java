package com.redjen.yanolja.controller;

import com.redjen.yanolja.model.dto.ReviewReplyDTO;
import com.redjen.yanolja.model.dto.ReviewWriteDTO;
import com.redjen.yanolja.model.vo.ReviewVO;
import com.redjen.yanolja.service.ReviewService;
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
@RequestMapping("/api")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/write")
    @ApiOperation(value="후기 작성", notes="실 사용 후 예약에 대한 후기를 작성한다.")
    public ResponseEntity<Map<String, Object>> writeNewReview(@RequestBody ReviewWriteDTO newReview) {
        Map<String, Object> resultMap = new HashMap<>();
        int result = reviewService.insertNewReview(newReview);
        resultMap.put("resultCode", result);
        resultMap.put("resultMsg", "후기가 정상적으로 작성되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/review/reply")
    @ApiOperation(value="후기 답변 작성", notes="후기에 대한 답변을 작성한다.")
    public ResponseEntity<Map<String, Object>> writeReplyToReview(@RequestBody ReviewReplyDTO reviewReply) {
        Map<String, Object> resultMap = new HashMap<>();
        int result = reviewService.insertReplyToReview(reviewReply);
        resultMap.put("resultCode", result);
        resultMap.put("resultMsg", "답변이 정상적으로 저장되었습니다.");
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/review/search")
    @ApiOperation(value="숙소 리뷰 목록 조회", notes="숙소의 모든 리뷰 목록을 조회한다.")
    public ResponseEntity<Map<String, Object>> searchReviewList(@RequestParam int companyIdx) {
        Map<String, Object> resultMap = new HashMap<>();
        List<ReviewVO> reviewVOList = reviewService.searchReviewList(companyIdx);
        resultMap.put("data", reviewVOList);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
