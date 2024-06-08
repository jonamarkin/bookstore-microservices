package com.markin.catalogservice.web.controllers;

import com.markin.catalogservice.AbstractIT;
import com.markin.catalogservice.domain.Product;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@Sql("/test-data.sql")
class ProductControllerTest extends AbstractIT {

    @Test
    void shouldReturnProducts(){
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products")
                .then()
                .statusCode(200)
                .body("data", hasSize(10))
                .body("totalElements", is(20))
                .body("pageNumber", is(1))
                .body("totalPages", is(2))
                .body("isFirst", is(true))
                .body("isLast", is(false))
                .body("hasNext", is(true))
                .body("hasPrevious", is(false));

    }


    @Test
    void shouldGetProductByCode() {
        Product product = given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", "PRD001")
                .then()
                .statusCode(200)
                .assertThat()
                .extract()
                .body()
                .as(Product.class);

        assertThat(product.code()).isEqualTo("PRD001");
        assertThat(product.name()).isEqualTo("To Kill a Mockingbird");
        assertThat(product.description()).isEqualTo("A novel by Harper Lee published in 1960, which won the 1961 Pulitzer Prize.");
        assertThat(product.price()).isEqualTo(new BigDecimal("10.99"));
    }

    @Test
    void shouldReturnNotFoundWhenProductCodeNotExists() {
        String code = "invalid_product_code";
        given().contentType(ContentType.JSON)
                .when()
                .get("/api/products/{code}", code)
                .then()
                .statusCode(404)
                .body("status", is(404))
                .body("title", is("Product Not Found"))
                .body("detail", is("Product with code " + code + " not found"));
    }

}