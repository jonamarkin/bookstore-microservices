package com.markin.orderservice.domain.models;

public record OrderSummary(
        String orderNumber,
        OrderStatus status
) {
}
