package com.example.dto;

import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Data
public class AddressDto {
    @NotBlank
    private String street;

    @NotBlank
    private String suite;

    @NotBlank
    private String city;

    @NotBlank
    private String zipcode;

    @Valid
    @NotNull
    private GeoDto geo;
} 