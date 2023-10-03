package com.mistergamarra.shopnow.ordersvc.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
