package com.markin.orderservice.domain;

public class OrderNotFoundException extends RuntimeException{

    public OrderNotFoundException(String message){
        super(message);
    }

    public OrderNotFoundException forOrderNumber(String orderNumber){
        return new OrderNotFoundException("Order with number " + orderNumber + " not found");
    }
}