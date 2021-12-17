package com.redjen.yanolja.service;

import com.redjen.yanolja.mapper.ReviewMapper;
import com.redjen.yanolja.model.dto.ReviewReplyDTO;
import com.redjen.yanolja.model.dto.ReviewWriteDTO;
import com.redjen.yanolja.model.vo.ReviewVO;
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
    public int insertNewReview(ReviewWriteDTO reviewWriteDTO) {
        //dto to vo?
        return reviewMapper.insertNewReview(reviewWriteDTO);
    }

    @Override
    public int insertReplyToReview(ReviewReplyDTO reviewReplyDTO) {
        return reviewMapper.insertReplyToReview(reviewReplyDTO);
    }

    @Override
    public List<ReviewVO> searchReviewList(int companyIdx) {
        return reviewMapper.searchReviewList(companyIdx);
    }
}
