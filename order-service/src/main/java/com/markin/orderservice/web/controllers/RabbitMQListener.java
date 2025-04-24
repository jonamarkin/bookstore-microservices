package com.markin.orderservice.web.controllers;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
class RabbitMQListener {

    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void handleNewOrder(MyPayload payload) {
        // Handle the new order message
        System.out.println("Received new order: " + payload.content());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue}")
    public void handleDeliveredOrder(MyPayload payload) {
        // Handle the delivered order message
        System.out.println("Received delivered order: " + payload.content());
    }
}
