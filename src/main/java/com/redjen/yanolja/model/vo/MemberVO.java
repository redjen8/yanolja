package com.redjen.yanolja.model.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redjen.yanolja.security.SecurityRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Min;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MemberVO {
    private int memberIdx;
    private int cartItemIdx;

    @Email
    private String email;

    @JsonIgnore
    private String password;

    private String phoneNumber;

    @Min(0)
    private int point;
    private String memberNick;
    private String memberStatus;
    private String memberImageUrl;
    private String createdAt;
    private String activatedAt;

    private SecurityRole securityRole;
}
