package com.markin.notificationservice.domain.models;

import com.markin.notificationservice.domain.models.Address;
import com.markin.notificationservice.domain.models.Customer;
import com.markin.notificationservice.domain.models.OrderItem;

import java.time.LocalDateTime;
import java.util.Set;

public record OrderDeliveredEvent(
        String eventId,
        String orderNumber,
        Set<OrderItem> items,
        Customer customer,
        Address deliveryAddress,
        LocalDateTime createdAt
) {
}
