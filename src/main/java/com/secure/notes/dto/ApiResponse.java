package com.secure.notes.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // don't show null fields in JSON
// fine to use it only in the controller layer for the HTTP requests and responses
public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private String errorCode;
    private LocalDateTime timestamp;

    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiResponse(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    //static factory methods (this is what you'll use most)

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "Request successful", data);
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(true, message, data);
    }

    public static <T> ApiResponse<T> error(String message) {
        ApiResponse<T> response = new ApiResponse<>(false, message, null);
        return response;
    }

    public static <T> ApiResponse<T> error(String message, String errorCode) {
        ApiResponse<T> response = new ApiResponse<>(false, message, null);
        response.setErrorCode(errorCode);
        return response;
    }
}
