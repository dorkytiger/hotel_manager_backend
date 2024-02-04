package com.dorkytiger.hotel_manager.emun;

import lombok.Getter;

@Getter
public enum Code {
    SUCCESS(200),
    FAIL(400),
    UNAUTHORIZED(401),
    UNKNOWN_EXCEPTION(500);
    private final int code;
    Code(int code) {
        this.code = code;
    }
}
