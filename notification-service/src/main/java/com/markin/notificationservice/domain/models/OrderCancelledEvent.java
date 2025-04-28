package com.markin.notificationservice.domain.models;

import com.markin.notificationservice.domain.models.Address;
import com.markin.notificationservice.domain.models.Customer;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderCancelledEvent(
        String eventId,
        String orderNumber,
        Set<OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        String reason,
        LocalDateTime createdAt
) {
}
