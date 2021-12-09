package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewMapper {

    int insertNewReview(int memberIdx, int companyIdx, int roomIdx, int reserveIdx, float rating, String reviewDescription);

    int insertReplyToReview(int reviewIdx, String reviewReply);

    List<ReviewVO> searchReviewList(int companyIdx);
}

