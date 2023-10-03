package com.mistergamarra.shopnow.warehousesvc.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
}
