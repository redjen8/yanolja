package com.redjen.yanolja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Company {

    private int companyIdx;
    private String companyName;
    private float companyRating;
    private int companyReviewCnt;
    private int minPrice;
    private String phoneNumber;
    private String availTimeStart;
    private String availTimeEnd;
    private String category;
    private int locationIdx;
    private String locationDescript;
    private String reservePolicy;
    private String companyStatus;

    private String companyImageUrl;
}
