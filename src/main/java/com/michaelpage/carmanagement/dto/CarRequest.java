package com.michaelpage.carmanagement.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarRequest {
    @NotBlank(message = "Brand is required")
    private String brand;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Car year is required")
    @Min(value = 1920, message = "Year must be 1920 or later")
    @Max(value = 2024, message = "Year cannot be in the future")
    private Integer carYear;

    @NotBlank(message = "License plate is required")
    private String licensePlate;

    @NotBlank(message = "Color is required")
    private String color;
} 