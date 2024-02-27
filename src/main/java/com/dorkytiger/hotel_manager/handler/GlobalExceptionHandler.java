package com.dorkytiger.hotel_manager.handler;

import com.dorkytiger.hotel_manager.model.common.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseEntity<Object> exceptionHandler(Exception e) {
        logger.error("未知异常！原因是:{}", e.getMessage());
        return ResponseEntity.fail(e.getMessage());
    }
}
