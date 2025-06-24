package com.michaelpage.carmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserId")
    private Integer userId;

    @NotBlank(message = "Username is required")
    @Column(name = "UserName", nullable = false, length = 100)
    private String userName;

    @NotBlank(message = "Password is required")
    @Column(name = "PasswordHash", nullable = false)
    @JsonIgnore
    private String passwordHash;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "FirstName")
    private String firstName;

    @Column(name = "LastName")
    private String lastName;

    @Column(name = "PhoneNumber")
    private String phoneNumber;
    
    @Column(name = "UserAddress")
    private String userAddress;

    @Column(name = "IsActive", nullable = false)
    private boolean isActive = true;

    @Column(name = "UserRole", nullable = false)
    private String userRole = "User";

    @Column(name = "CreatedDate", nullable = false, updatable = false)
    @JsonIgnore
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "LastLoginDate")
    @JsonIgnore
    private LocalDateTime lastLoginDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Car> cars = new ArrayList<>();
} 