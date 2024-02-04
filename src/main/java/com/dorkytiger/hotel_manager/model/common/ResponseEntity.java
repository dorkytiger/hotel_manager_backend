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

    public ResponseEntity<T> success(T data) {
        return new ResponseEntity<T>(Code.SUCCESS.getCode(), Message.SUCCESS.getMessage(), data);
    }

    public ResponseEntity<T> success() {
        return new ResponseEntity<T>(Code.SUCCESS.getCode(), Message.SUCCESS.getMessage(), null);
    }

    public ResponseEntity<T> fail() {
        return new ResponseEntity<T>(Code.FAIL.getCode(), Message.FAIL.getMessage(), null);
    }

    public ResponseEntity<T> fail(String message) {
        return new ResponseEntity<T>(Code.FAIL.getCode(), message, null);
    }

    public ResponseEntity<T> fail(Integer code, String message) {
        return new ResponseEntity<T>(code, message, null);
    }
}
