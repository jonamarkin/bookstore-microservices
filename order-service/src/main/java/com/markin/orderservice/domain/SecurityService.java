package com.markin.orderservice.domain;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    public String getLoginUsername(){
        return "user";
    }
}
