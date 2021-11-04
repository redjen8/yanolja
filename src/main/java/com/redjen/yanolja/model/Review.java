package com.redjen.yanolja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Review {

    private int reviewIdx;
    private int memberIdx;
    private int companyIdx;
    private int roomIdx;
    private int reserveIdx;
    private float rating;
    private String reviewDescription;
    private String reviewReply;
    private String createdAt;

    private Room room;
    private Member member;
}
