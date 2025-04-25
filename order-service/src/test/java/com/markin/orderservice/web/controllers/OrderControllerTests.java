package com.markin.orderservice.web.controllers;


import com.markin.orderservice.AbstractIT;
import com.markin.orderservice.testdata.TestDataFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class OrderControllerTests extends AbstractIT {

    @Nested
    class CreateOrderTests {
        @Test
        void shouldCreateOrderSuccessfully(){
            var payload = """
                    {
                       "items": [
                         {
                           "code": "P001",
                           "name": "Book: Java in Depth",
                           "price": 29.99,
                           "quantity": 2
                         },
                         {
                           "code": "P002",
                           "name": "Notebook",
                           "price": 9.99,
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
}
