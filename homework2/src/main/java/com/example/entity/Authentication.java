package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "authentication")
@Data
public class Authentication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;
} 