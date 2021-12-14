package com.redjen.yanolja.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Setter
@Getter
@AllArgsConstructor
public class CompanyQueryResultDTO {
    @NotNull
    String companyName;
    @NotNull
    float companyRating;
    int minPrice;
    int companyReviewCnt;
    String companyImageUrl;
    Timestamp availTimeStart;
    String category;
}
