package com.example.bakingmanagement.DTO;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record BakingRequest(
        @NotBlank(message = "Product name is required")
        @Size(max = 80, message = "Product cannot exceed 80 characters")
        String productName,

        @NotBlank(message = "Category is required")
        String category,

        @NotNull(message = "Price is required")
        @Positive(message = "Price must be greater than zero")
        double price,

        @PositiveOrZero(message = "Quantity cannot be negative")
        int quantity,

        @NotBlank(message = "Location is required")
        String location,


        @NotNull(message = "Baking date is required")
        @FutureOrPresent(message = "Baking date can only be today or a date in future")
        LocalDate bakingDate
) {
}
