package com.redjen.yanolja.mapper;

import com.redjen.yanolja.model.dto.ReviewWriteDTO;
import com.redjen.yanolja.model.vo.ReviewVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ReviewMapper {

    int insertNewReview(ReviewWriteDTO reviewWriteDTO);

    int insertReplyToReview(int reviewIdx, String reviewReply);

    List<ReviewVO> searchReviewList(int companyIdx);
}

