package com.example.sysark_lab2;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;


public record Product(@NotNull(message = "Must have an ID")
                      int id,
                      @NotBlank(message = "Name cannot be empty")
                      String name,
                      @NotNull(message = "Category cannot be null")
                      Category category,
                      @Min(value = 1, message = "Rating must be at least 1")
                      @Max(value = 10, message = "Rating must be at most 10")
                      int rating,
                      LocalDate createdDate,
                      LocalDate modifiedDate) {
}







