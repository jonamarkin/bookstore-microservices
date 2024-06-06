package com.markin.catalogservice.domain;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String code){
        super(code);
    }

    public static ProductNotFoundException forCode(String code){
        return new ProductNotFoundException("Product with code " + code+ " not found");
    }
}
