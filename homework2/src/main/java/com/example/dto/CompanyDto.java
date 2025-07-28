package com.example.dto;

import lombok.Data;
import jakarta.validation.constraints.*;

@Data
public class CompanyDto {
    @NotBlank
    private String name;

    @NotBlank
    private String catchPhrase;

    @NotBlank
    private String bs;
} 