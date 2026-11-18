package com.example.bakingmanagement.DTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record BakingRequest(
        @NotBlank(message = "Product name is required")
        @Size(max = 80, message = "Product cannot exceed 80 characters")
        String productName,

        @NotBlank(message = "Category is required")
        String category,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than zero")
        BigDecimal price,

        @PositiveOrZero(message = "Quantity cannot be negative")
        int quantity
) {
}
