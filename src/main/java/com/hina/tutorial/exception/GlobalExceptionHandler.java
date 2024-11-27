package com.hina.tutorial.exception;

import com.hina.tutorial.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // khai báo đây là class để xử lý ngoại lệ
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class) //xử lý các lỗi thuộc class Exception
    ResponseEntity<ApiResponse<?>> handleRuntimeException(Exception e) {
        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AppException.class) //xử lý các lỗi thuộc class AppException
    ResponseEntity<ApiResponse<?>> handleRuntimeException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }


    //valid
    @ExceptionHandler(value = MethodArgumentNotValidException.class)//xử lý các lỗi thuộc class MethodArgumentNotValidException
    ResponseEntity<ApiResponse<?>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();

        ErrorCode errorCode;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e1) {
            errorCode = ErrorCode.INVALID_KEY;
        }

        ApiResponse<?> apiResponse = new ApiResponse<>();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }
}
