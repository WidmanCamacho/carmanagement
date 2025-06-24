package com.michaelpage.carmanagement.service;

import com.michaelpage.carmanagement.dto.CarRequest;
import com.michaelpage.carmanagement.dto.CarResponse;
import com.michaelpage.carmanagement.model.Car;
import com.michaelpage.carmanagement.model.User;
import com.michaelpage.carmanagement.repository.CarRepository;
import com.michaelpage.carmanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    public CarResponse createCar(String userEmail, CarRequest request) {
        User user = getUserByEmail(userEmail);
        
        Car car = new Car();
        car.setUser(user);
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setCarYear(request.getCarYear());
        car.setLicensePlate(request.getLicensePlate());
        car.setColor(request.getColor());
        car.setCreatedDate(LocalDateTime.now());
        
        Car savedCar = carRepository.save(car);
        return mapToCarResponse(savedCar);
    }

    public List<CarResponse> getUserCars(String userEmail) {
        User user = getUserByEmail(userEmail);
        return carRepository.findByUserUserId(user.getUserId()).stream()
                .map(this::mapToCarResponse)
                .collect(Collectors.toList());
    }

    public CarResponse getCar(String userEmail, Integer carId) {
        Car car = getCarEntity(userEmail, carId);
        return mapToCarResponse(car);
    }

    public CarResponse updateCar(String userEmail, Integer carId, CarRequest request) {
        Car car = getCarEntity(userEmail, carId);
        
        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setCarYear(request.getCarYear());
        car.setLicensePlate(request.getLicensePlate());
        car.setColor(request.getColor());
        
        Car updatedCar = carRepository.save(car);
        return mapToCarResponse(updatedCar);
    }

    public void deleteCar(String userEmail, Integer carId) {
        Car car = getCarEntity(userEmail, carId);
        carRepository.delete(car);
    }

    private Car getCarEntity(String userEmail, Integer carId) {
        User user = getUserByEmail(userEmail);
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));
        
        if (!car.getUser().getUserId().equals(user.getUserId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access denied");
        }
        
        return car;
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    private CarResponse mapToCarResponse(Car car) {
        return CarResponse.builder()
                .carId(car.getCarId())
                .brand(car.getBrand())
                .model(car.getModel())
                .carYear(car.getCarYear())
                .licensePlate(car.getLicensePlate())
                .color(car.getColor())
                .owner(CarResponse.UserSummary.builder()
                        .userId(car.getUser().getUserId())
                        .userName(car.getUser().getUserName())
                        .email(car.getUser().getEmail())
                        .firstName(car.getUser().getFirstName())
                        .lastName(car.getUser().getLastName())
                        .build())
                .build();
    }
} 