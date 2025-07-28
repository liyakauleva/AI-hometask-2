package com.example.mapper;

import com.example.dto.*;
import com.example.entity.*;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setPhone(user.getPhone());
        dto.setWebsite(user.getWebsite());
        dto.setAddress(toDto(user.getAddress()));
        dto.setCompany(toDto(user.getCompany()));
        return dto;
    }
    public AddressDto toDto(Address address) {
        if (address == null) return null;
        AddressDto dto = new AddressDto();
        dto.setStreet(address.getStreet());
        dto.setSuite(address.getSuite());
        dto.setCity(address.getCity());
        dto.setZipcode(address.getZipcode());
        dto.setGeo(toDto(address.getGeo()));
        return dto;
    }
    public GeoDto toDto(Geo geo) {
        if (geo == null) return null;
        GeoDto dto = new GeoDto();
        dto.setLat(geo.getLat());
        dto.setLng(geo.getLng());
        return dto;
    }
    public CompanyDto toDto(Company company) {
        if (company == null) return null;
        CompanyDto dto = new CompanyDto();
        dto.setName(company.getName());
        dto.setCatchPhrase(company.getCatchPhrase());
        dto.setBs(company.getBs());
        return dto;
    }
    public User toEntity(UserDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setWebsite(dto.getWebsite());
        user.setAddress(toEntity(dto.getAddress()));
        user.setCompany(toEntity(dto.getCompany()));
        return user;
    }
    public Address toEntity(AddressDto dto) {
        if (dto == null) return null;
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setSuite(dto.getSuite());
        address.setCity(dto.getCity());
        address.setZipcode(dto.getZipcode());
        address.setGeo(toEntity(dto.getGeo()));
        return address;
    }
    public Geo toEntity(GeoDto dto) {
        if (dto == null) return null;
        Geo geo = new Geo();
        geo.setLat(dto.getLat());
        geo.setLng(dto.getLng());
        return geo;
    }
    public Company toEntity(CompanyDto dto) {
        if (dto == null) return null;
        Company company = new Company();
        company.setName(dto.getName());
        company.setCatchPhrase(dto.getCatchPhrase());
        company.setBs(dto.getBs());
        return company;
    }
} 