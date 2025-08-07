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
import com.example.mapper.UserMapper;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserDto updateUser(Long id, UserDto userDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        existing.setName(userDto.getName());
        existing.setUsername(userDto.getUsername());
        existing.setEmail(userDto.getEmail());
        existing.setPhone(userDto.getPhone());
        existing.setWebsite(userDto.getWebsite());
        existing.setAddress(userMapper.toEntity(userDto.getAddress()));
        existing.setCompany(userMapper.toEntity(userDto.getCompany()));
        return userMapper.toDto(userRepository.save(existing));
    }

    @Override
    @Transactional
    public UserDto patchUser(Long id, UserDto userDto) {
        User existing = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
        if (userDto.getName() != null) existing.setName(userDto.getName());
        if (userDto.getUsername() != null) existing.setUsername(userDto.getUsername());
        if (userDto.getEmail() != null) existing.setEmail(userDto.getEmail());
        if (userDto.getPhone() != null) existing.setPhone(userDto.getPhone());
        if (userDto.getWebsite() != null) existing.setWebsite(userDto.getWebsite());
        if (userDto.getAddress() != null) existing.setAddress(userMapper.toEntity(userDto.getAddress()));
        if (userDto.getCompany() != null) existing.setCompany(userMapper.toEntity(userDto.getCompany()));
        return userMapper.toDto(userRepository.save(existing));
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
} 