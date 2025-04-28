package com.markin.notificationservice.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.markin.notificationservice.AbstractIT;
import com.markin.notificationservice.ApplicationProperties;
import com.markin.notificationservice.domain.models.*;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.verify;

class OrderEventHandlerTests extends AbstractIT {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ApplicationProperties properties;

    Customer customer = new Customer("Siva", "siva@gmail.com", "999999999");
    Address address = new Address("addr line 1", null, "Hyderabad", "TS", "500072", "India");

//    @Test
//    void shouldHandleOrderCreatedEvent() {
//        String orderNumber = UUID.randomUUID().toString();
//
//        var event = new OrderCreatedEvent(
//                UUID.randomUUID().toString(), orderNumber, Set.of(), customer, address, LocalDateTime.now());
//        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), properties.newOrdersQueue(), event);
//
//        await().atMost(30, SECONDS).untilAsserted(() -> {
//            verify(notificationService).sendOrderCreatedNotification(any(OrderCreatedEvent.class));
//        });
//    }
//
//    @Test
//    void shouldHandleOrderDeliveredEvent() {
//        String orderNumber = UUID.randomUUID().toString();
//
//        var event = new OrderDeliveredEvent(
//                UUID.randomUUID().toString(), orderNumber, Set.of(), customer, address, LocalDateTime.now());
//        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), properties.deliveredOrdersQueue(), event);
//
//        await().atMost(30, SECONDS).untilAsserted(() -> {
//            verify(notificationService).sendOrderDeliveredNotification(any(OrderDeliveredEvent.class));
//        });
//    }
//
//    @Test
//    void shouldHandleOrderCancelledEvent() {
//        String orderNumber = UUID.randomUUID().toString();
//
//        var event = new OrderCancelledEvent(
//                UUID.randomUUID().toString(),
//                orderNumber,
//                Set.of(),
//                customer,
//                address,
//                "test cancel reason",
//                LocalDateTime.now());
//        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), properties.cancelledOrdersQueue(), event);
//
//        await().atMost(30, SECONDS).untilAsserted(() -> {
//            verify(notificationService).sendOrderCancelledNotification(any(OrderCancelledEvent.class));
//        });
//    }
//
//    @Test
//    void shouldHandleOrderErrorEvent() {
//        String orderNumber = UUID.randomUUID().toString();
//
//        var event = new OrderErrorEvent(
//                UUID.randomUUID().toString(),
//                orderNumber,
//                Set.of(),
//                customer,
//                address,
//                "test error reason",
//                LocalDateTime.now());
//        rabbitTemplate.convertAndSend(properties.orderEventsExchange(), properties.errorOrdersQueue(), event);
//
//        await().atMost(30, SECONDS).untilAsserted(() -> {
//            verify(notificationService).sendOrderErrorEventNotification(any(OrderErrorEvent.class));
//        });
//    }
}
