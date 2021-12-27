package com.redjen.yanolja.security;

import lombok.Getter;

@Getter
public enum Authority {
    ROLE_USER("사용자", "일반 사용자 권한"), ROLE_ADMIN("사업자", "사업주 계정 권한");

    private final String role;
    private final String description;

    Authority(String role, String description) {
        this.role = role;
        this.description = description;
    }
}
