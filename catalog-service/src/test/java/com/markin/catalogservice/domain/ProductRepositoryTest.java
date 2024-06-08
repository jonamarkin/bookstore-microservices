package com.markin.catalogservice.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest(
        properties = {
                "spring.test.database.replace=none",
                "spring.datasource.url=jdbc:tc:postgresql:16-alpine:///db",
        }
)
@Sql("/test-data.sql")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void shouldGetAllProducts(){
        List<ProductEntity> productEntityList = productRepository.findAll();
        assertThat(productEntityList).hasSize(20);
    }

    @Test
    void shouldGetProductByCode() {
        ProductEntity product = productRepository.findByCode("PRD001").orElseThrow();
        assertThat(product.getCode()).isEqualTo("PRD001");
        assertThat(product.getName()).isEqualTo("To Kill a Mockingbird");
        assertThat(product.getDescription()).isEqualTo("A novel by Harper Lee published in 1960, which won the 1961 Pulitzer Prize.");
        assertThat(product.getPrice()).isEqualTo(new BigDecimal("10.99"));
    }

    @Test
    void shouldReturnEmptyWhenProductCodeNotExists() {
        assertThat(productRepository.findByCode("invalid_product_code")).isEmpty();
    }

}