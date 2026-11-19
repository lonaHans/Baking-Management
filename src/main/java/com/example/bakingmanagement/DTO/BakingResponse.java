package com.example.bakingmanagement.DTO;

import com.example.bakingmanagement.Enum.StockStatus;

import java.time.LocalDate;

public record BakingResponse(Long id, String productName, String category, double price, int quantity, String location, LocalDate bakingDate,
                             StockStatus stockStatus) {
}
