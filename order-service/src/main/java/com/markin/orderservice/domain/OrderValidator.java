package com.markin.orderservice.domain;

import com.markin.orderservice.clients.catalog.Product;
import com.markin.orderservice.clients.catalog.ProductServiceClient;
import com.markin.orderservice.domain.models.CreateOrderRequest;
import com.markin.orderservice.domain.models.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class OrderValidator {
    private static final Logger log = LoggerFactory.getLogger(OrderService.class);

    private final ProductServiceClient productServiceClient;

    OrderValidator(ProductServiceClient productServiceClient) {
        this.productServiceClient = productServiceClient;
    }

    void validate(CreateOrderRequest createOrderRequest) {
        Set<OrderItem> items = createOrderRequest.items();
        for (OrderItem item : items) {
            Product product = productServiceClient.getProductByCode(item.code())
                    .orElseThrow(() -> new InvalidOrderException("Invalid product code: " + item.code()));

            if(item.price().compareTo(product.price())!=0){
                log.error(
                        "Price mismatch for product code {}: expected {}, but got {}",
                        item.code(), product.price(), item.price()
                );
                throw new InvalidOrderException("Price mismatch for product code: " + item.code());
            }
        }
    }
}
