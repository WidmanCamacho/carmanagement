package com.michaelpage.carmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CarId")
    private Integer carId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    @JsonIgnoreProperties({"cars", "passwordHash", "createdDate", "lastLoginDate"})
    private User user;

    @NotBlank(message = "Brand is required")
    @Column(name = "Brand", nullable = false)
    private String brand;

    @NotBlank(message = "Model is required")
    @Column(name = "Model", nullable = false)
    private String model;

    @NotNull(message = "Car year is required")
    @Min(value = 1920, message = "Year must be 1920 or later")
    @Max(value = 2024, message = "Year cannot be in the future")
    @Column(name = "CarYear", nullable = false)
    private Integer carYear;

    @NotBlank(message = "License plate is required")
    @Column(name = "LicensePlate", nullable = false)
    private String licensePlate;

    @NotBlank(message = "Color is required")
    @Column(name = "Color", nullable = false)
    private String color;

    @Column(name = "CreatedDate", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();
} 