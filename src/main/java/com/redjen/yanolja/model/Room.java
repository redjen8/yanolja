package com.redjen.yanolja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    private int roomIdx;
    private int companyIdx;

    @NotNull
    private String roomName;

    @NotNull
    private String roomShortInfo;

    @Min(0)
    private int roomRemainCnt;

    private boolean bSookbak;
    private boolean bDaesil;

    @Min(0)
    private int maxPersonCnt;

    @Min(0)
    private int defaultPersonCnt;
    private String roomDescription;
    private String roomStatus;
    private String roomCategory;
    private int roomPrice;
}
