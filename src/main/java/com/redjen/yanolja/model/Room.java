package com.redjen.yanolja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Room {
    private int roomIdx;
    private int companyIdx;
    private String roomName;
    private String roomShortInfo;
    private int roomRemainCnt;
    private boolean bSookbak;
    private boolean bDaesil;
    private int maxPersonCnt;
    private int defaultPersonCnt;
    private String roomDescription;
    private String roomStatus;
    private String roomCategory;
    private int roomPrice;
}
