package com.redjen.yanolja.model;

import lombok.Data;

@Data
public class Member {
    private int memberIdx;
    private int cartItemIdx;
    private String email;
    private String password;
    private String phoneNumber;
    private int point;
    private String nickname;
    private String memberStatus;
    private String memberImageUrl;
    private String createdAt;
    private String activatedAt;
}
