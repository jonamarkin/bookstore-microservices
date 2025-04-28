package com.markin.notificationservice.events;

import com.markin.notificationservice.domain.models.OrderCreatedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    @RabbitListener(queues = "${notifications.new-orders-queue}")
    void handleOrderCreatedEvent(OrderCreatedEvent event) {
        // Handle order created event
        System.out.println("Order created: " + event.eventId());
    }
}
