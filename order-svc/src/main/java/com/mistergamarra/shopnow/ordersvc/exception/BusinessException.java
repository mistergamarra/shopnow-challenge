package com.mistergamarra.shopnow.ordersvc.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
