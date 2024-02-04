package com.dorkytiger.hotel_manager.emun;

import lombok.Getter;

@Getter
public enum Message {
    SUCCESS("成功"),
    FAIL("失败"),
    UNKNOWN_EXCEPTION("未知异常"),
    TOKEN_IS_NULL("token为空"),
    TOKEN_IS_ERROR("token错误"),
    ;
    private final String message;

    Message(String message) {
        this.message = message;
    }
}
