package com.hina.tutorial.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "UNCATEGORIZED error"),
    INVALID_KEY(1000, "Invalid key"),
    USER_EXISTED(1001, "User exited"),
    USER_INVALID(1002, "Username must be at least 3 character"),
    INVALID_PASSWORD(1003, "Password must be at least 8 character"),
    USER_NOT_EXISTED(1004, "User not existed"),
    UNAUTHENTICATED(1005, "Unauthenticated"),
    ;
    private final int code;
    private final String message;
}
