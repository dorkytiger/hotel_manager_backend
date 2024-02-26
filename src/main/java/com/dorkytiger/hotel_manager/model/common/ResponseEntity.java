package com.dorkytiger.hotel_manager.model.common;

import com.dorkytiger.hotel_manager.emun.Code;
import com.dorkytiger.hotel_manager.emun.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private int code;
    private String message;
    private T data;

    public static <T> ResponseEntity<T> success(T data) {
        return new ResponseEntity<>(Code.SUCCESS.getCode(), Message.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseEntity<T> success() {
        return new ResponseEntity<>(Code.SUCCESS.getCode(), Message.SUCCESS.getMessage(), null);
    }

    public static <T> ResponseEntity<T> fail(String message) {
        return new ResponseEntity<>(Code.FAIL.getCode(), message, null);
    }

    public static <T> ResponseEntity<T> fail(Integer code, String message) {
        return new ResponseEntity<>(code, message, null);
    }
}
