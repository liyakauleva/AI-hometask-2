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
    @AttributeOverrides({
        @AttributeOverride(name = "lat", column = @Column(name = "geo_lat")),
        @AttributeOverride(name = "lng", column = @Column(name = "geo_lng"))
    })
    private Geo geo;
} 