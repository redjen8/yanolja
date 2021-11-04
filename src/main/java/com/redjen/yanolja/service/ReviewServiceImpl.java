package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.MemberMapper;
import com.redjen.yanolja.mapper.ReviewMapper;
import com.redjen.yanolja.model.Member;
import com.redjen.yanolja.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{
    private ReviewMapper reviewMapper;

    @Autowired
    public ReviewServiceImpl(ReviewMapper reviewMapper) {
        this.reviewMapper = reviewMapper;
    }

    @Override
    public int insertNewReview(int memberIdx, int companyIdx, int roomIdx, int reserveIdx, float rating, String reviewDescription) {
        return reviewMapper.insertNewReview(memberIdx, companyIdx, roomIdx, reserveIdx, rating, reviewDescription);
    }

    @Override
    public int insertReplyToReview(int reviewIdx, String reviewReply) {
        return reviewMapper.insertReplyToReview(reviewIdx, reviewReply);
    }

    @Override
    public List<Review> searchReviewList(int companyIdx) {
        return reviewMapper.searchReviewList(companyIdx);
    }
}
