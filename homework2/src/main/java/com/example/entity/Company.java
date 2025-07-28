package com.example.entity;

import jakarta.persistence.*;
import lombok.Data;

@Embeddable
@Data
public class Company {
    private String name;
    private String catchPhrase;
    private String bs;
} 