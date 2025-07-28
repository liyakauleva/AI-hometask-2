package com.example.service.impl;

import com.example.dto.*;
import com.example.entity.*;
import com.example.repository.UserRepository;
import com.example.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.service.UserService;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = toEntity(userDto);
        return toDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        existing.setName(userDto.getName());
        existing.setUsername(userDto.getUsername());
        existing.setEmail(userDto.getEmail());
        existing.setPhone(userDto.getPhone());
        existing.setWebsite(userDto.getWebsite());
        existing.setAddress(toEntity(userDto.getAddress()));
        existing.setCompany(toEntity(userDto.getCompany()));
        return toDto(userRepository.save(existing));
    }

    @Override
    public UserDto patchUser(Long id, UserDto userDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        if (userDto.getName() != null) existing.setName(userDto.getName());
        if (userDto.getUsername() != null) existing.setUsername(userDto.getUsername());
        if (userDto.getEmail() != null) existing.setEmail(userDto.getEmail());
        if (userDto.getPhone() != null) existing.setPhone(userDto.getPhone());
        if (userDto.getWebsite() != null) existing.setWebsite(userDto.getWebsite());
        if (userDto.getAddress() != null) existing.setAddress(toEntity(userDto.getAddress()));
        if (userDto.getCompany() != null) existing.setCompany(toEntity(userDto.getCompany()));
        return toDto(userRepository.save(existing));
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Mapping methods
    private UserDto toDto(User user) {
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
    private AddressDto toDto(Address address) {
        if (address == null) return null;
        AddressDto dto = new AddressDto();
        dto.setStreet(address.getStreet());
        dto.setSuite(address.getSuite());
        dto.setCity(address.getCity());
        dto.setZipcode(address.getZipcode());
        dto.setGeo(toDto(address.getGeo()));
        return dto;
    }
    private GeoDto toDto(Geo geo) {
        if (geo == null) return null;
        GeoDto dto = new GeoDto();
        dto.setLat(geo.getLat());
        dto.setLng(geo.getLng());
        return dto;
    }
    private CompanyDto toDto(Company company) {
        if (company == null) return null;
        CompanyDto dto = new CompanyDto();
        dto.setName(company.getName());
        dto.setCatchPhrase(company.getCatchPhrase());
        dto.setBs(company.getBs());
        return dto;
    }
    private User toEntity(UserDto dto) {
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
    private Address toEntity(AddressDto dto) {
        if (dto == null) return null;
        Address address = new Address();
        address.setStreet(dto.getStreet());
        address.setSuite(dto.getSuite());
        address.setCity(dto.getCity());
        address.setZipcode(dto.getZipcode());
        address.setGeo(toEntity(dto.getGeo()));
        return address;
    }
    private Geo toEntity(GeoDto dto) {
        if (dto == null) return null;
        Geo geo = new Geo();
        geo.setLat(dto.getLat());
        geo.setLng(dto.getLng());
        return geo;
    }
    private Company toEntity(CompanyDto dto) {
        if (dto == null) return null;
        Company company = new Company();
        company.setName(dto.getName());
        company.setCatchPhrase(dto.getCatchPhrase());
        company.setBs(dto.getBs());
        return company;
    }
} 