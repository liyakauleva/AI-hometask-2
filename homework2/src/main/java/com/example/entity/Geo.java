package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Geo {
    private String lat;
    private String lng;
} 