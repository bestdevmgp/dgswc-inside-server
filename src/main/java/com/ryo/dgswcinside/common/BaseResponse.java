package com.ryo.dgswcinside.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private String message;
    private T data;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    
    public static <T> BaseResponse<T> ok(T data) {
        return new BaseResponse<>(true, "Success", data, LocalDateTime.now());
    }
    
    public static <T> BaseResponse<T> ok(String message) {
        return new BaseResponse<>(true, message, null, LocalDateTime.now());
    }
    
    public static <T> BaseResponse<T> ok(String message, T data) {
        return new BaseResponse<>(true, message, data, LocalDateTime.now());
    }
    
    public static <T> BaseResponse<T> fail(String message) {
        return new BaseResponse<>(false, message, null, LocalDateTime.now());
    }
    
    public static <T> BaseResponse<T> fail(String message, T data) {
        return new BaseResponse<>(false, message, data, LocalDateTime.now());
    }
}