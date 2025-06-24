package com.michaelpage.carmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarResponse {
    private Integer carId;
    private String brand;
    private String model;
    private Integer carYear;
    private String licensePlate;
    private String color;
    private UserSummary owner;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserSummary {
        private Integer userId;
        private String userName;
        private String email;
        private String firstName;
        private String lastName;
    }
} 