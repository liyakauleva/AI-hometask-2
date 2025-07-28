package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String street;
    private String suite;
    private String city;
    private String zipcode;

    @Embedded
    private Geo geo;
} 