package com.markin.notificationservice.domain.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record Customer (
        @NotBlank(message = "Customer name is mandatory") String name,
        @NotBlank(message = "Customer email is mandatory") @Email String email,
        @NotBlank(message = "Customer phone number is mandatory") String phone
){}
