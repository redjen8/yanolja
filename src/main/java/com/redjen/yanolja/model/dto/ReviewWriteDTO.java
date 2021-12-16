package com.redjen.yanolja.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ReviewWriteDTO {

    int memberIdx;
    int companyIdx;
    int roomIdx;
    int reserveIdx;
    float rating;
    String reviewDescription;
}
