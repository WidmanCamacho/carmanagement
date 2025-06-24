package com.michaelpage.carmanagement.controller;

import com.michaelpage.carmanagement.dto.CarRequest;
import com.michaelpage.carmanagement.dto.CarResponse;
import com.michaelpage.carmanagement.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> createCar(
            @AuthenticationPrincipal UserDetails userDetails,
            @Valid @RequestBody CarRequest request
    ) {
        return ResponseEntity.ok(carService.createCar(userDetails.getUsername(), request));
    }

    @GetMapping
    public ResponseEntity<?> getUserCars(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(required = false) Integer id
    ) {
        if (id != null) {
            return ResponseEntity.ok(carService.getCar(userDetails.getUsername(), id));
        }
        return ResponseEntity.ok(carService.getUserCars(userDetails.getUsername()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarResponse> getCar(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        return ResponseEntity.ok(carService.getCar(userDetails.getUsername(), id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarResponse> updateCar(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id,
            @Valid @RequestBody CarRequest request
    ) {
        return ResponseEntity.ok(carService.updateCar(userDetails.getUsername(), id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Integer id
    ) {
        carService.deleteCar(userDetails.getUsername(), id);
        return ResponseEntity.noContent().build();
    }
} 