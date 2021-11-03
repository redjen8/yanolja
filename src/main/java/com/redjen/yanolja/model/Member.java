package com.redjen.yanolja.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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