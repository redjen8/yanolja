package com.redjen.yanolja.service;

import com.redjen.yanolja.model.dto.ReviewWriteDTO;
import com.redjen.yanolja.model.vo.ReviewVO;

import java.util.List;

public interface ReviewService {

    int insertNewReview(ReviewWriteDTO reviewWriteDTO);

    int insertReplyToReview(int reviewIdx, String reviewReply);

    List<ReviewVO> searchReviewList(int companyIdx);
}
