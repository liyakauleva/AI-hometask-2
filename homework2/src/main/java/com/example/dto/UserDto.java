package com.example.dto;

import lombok.Data;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

@Data
public class UserDto {
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String phone;

    @NotBlank
    private String website;

    @Valid
    @NotNull
    private AddressDto address;

    @Valid
    @NotNull
    private CompanyDto company;
} 