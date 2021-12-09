package com.redjen.yanolja.service;

import com.redjen.yanolja.model.vo.ReviewVO;

import java.util.List;

public interface ReviewService {

    int insertNewReview(int memberIdx, int companyIdx, int roomIdx, int reserveIdx, float rating, String reviewDescription);

    int insertReplyToReview(int reviewIdx, String reviewReply);

    List<ReviewVO> searchReviewList(int companyIdx);
}
