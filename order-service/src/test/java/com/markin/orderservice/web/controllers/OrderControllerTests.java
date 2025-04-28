package com.markin.orderservice.web.controllers;


import com.markin.orderservice.AbstractIT;
import com.markin.orderservice.domain.models.OrderSummary;
import com.markin.orderservice.testdata.TestDataFactory;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static wiremock.org.eclipse.jetty.http.HttpTokens.getToken;

@Sql("/test-orders.sql")
public class OrderControllerTests extends AbstractIT {

    @Nested
    class CreateOrderTests {
        @Test
        void shouldCreateOrderSuccessfully(){
            mockGetProductByCode("P001", "Book", new BigDecimal("29.99"));
            var payload = """
                    {
                       "items": [
                         {
                           "code": "P001",
                           "name": "Book",
                           "price": 29.99,
                           "quantity": 1
                         }
                       ],
                       "customer": {
                         "name": "Alice Johnson",
                         "email": "alice.johnson@example.com",
                         "phone": "+19876543210"
                       },
                       "deliveryAddress": {
                         "addressLine1": "456 Elm Street",
                         "addressLine2": "Suite 101",
                         "city": "Los Angeles",
                         "state": "CA",
                         "zipCode": "90001",
                         "country": "USA"
                       }
                     }
                """;

            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.CREATED.value())
                    .body("orderNumber", notNullValue());
        }

        @Test
        void shouldReturnBadRequestWhenMandatoryDataIsMissing(){
            var payload = TestDataFactory.createOrderRequestWithInvalidCustomer();
            given().contentType(ContentType.JSON)
                    .body(payload)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    class GetOrdersTests {
        @Test
        void shouldGetOrdersSuccessfully() {
            List<OrderSummary> orderSummaries = given().when()
                    .get("/api/orders")
                    .then()
                    .statusCode(200)
                    .extract()
                    .body()
                    .as(new TypeRef<>() {});

            assertThat(orderSummaries).hasSize(2);
        }
    }

    @Nested
    class GetOrderByOrderNumberTests {
        String orderNumber = "order-123";

        @Test
        void shouldGetOrderSuccessfully() {
            given().when()
                    .get("/api/orders/{orderNumber}", orderNumber)
                    .then()
                    .statusCode(200)
                    .body("orderNumber", is(orderNumber))
                    .body("items.size()", is(2));
        }
    }
}
