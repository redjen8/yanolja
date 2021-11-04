package com.redjen.yanolja.service;

import com.redjen.yanolja.model.Review;

import java.util.List;

public interface ReviewService {

    int insertNewReview(int memberIdx, int companyIdx, int roomIdx, int reserveIdx, float rating, String reviewDescription);

    int insertReplyToReview(int reviewIdx, String reviewReply);

    List<Review> searchReviewList(int companyIdx);
}
