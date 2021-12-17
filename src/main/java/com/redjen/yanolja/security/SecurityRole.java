package com.redjen.yanolja.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SecurityRole {
    GUEST("ROLE_GUEST"),
    MEMBER("ROLE_MEMBER"),
    ADMIN("ROLE_ADMIN");

    private final String key;
}
