package com.michaelpage.carmanagement.repository;

import com.michaelpage.carmanagement.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Integer> {
    List<Car> findByUserUserId(Integer userId);
} 