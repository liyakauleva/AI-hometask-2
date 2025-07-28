package com.example.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class GeoDto {
    @NotBlank
    private String lat;

    @NotBlank
    private String lng;
} 