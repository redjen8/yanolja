package com.redjen.yanolja.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ReviewVO {

    private int reviewIdx;
    private int memberIdx;
    private int companyIdx;
    private int roomIdx;
    private int reserveIdx;
    private float rating;
    private String reviewDescription;
    private String reviewReply;
    private String createdAt;

    private RoomVO roomVO;
    private MemberVO memberVO;
}
