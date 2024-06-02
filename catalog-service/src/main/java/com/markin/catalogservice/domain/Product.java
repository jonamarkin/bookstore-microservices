package com.markin.catalogservice.domain;

import jakarta.persistence.Column;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record Product(
        String code,

        String name,

        String description,

        String imageUrl,
        BigDecimal price
) {
}
