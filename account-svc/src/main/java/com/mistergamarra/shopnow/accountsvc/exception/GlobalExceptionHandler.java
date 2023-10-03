package com.mistergamarra.shopnow.accountsvc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ LoginException.class })
    public final ResponseEntity<ApiError> handleLoginException(LoginException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), null, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ BusinessException.class })
    public final ResponseEntity<ApiError> handleBusinessException(BusinessException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError(ex.getMessage()), null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Getter
    public static class ApiError{
            String msg;

         public ApiError(String msg) {
             this.msg = msg;
         }

        @Override
        public String toString() {
            return "ApiError{" +
                    "msg='" + msg + '\'' +
                    '}';
        }
    }

}

