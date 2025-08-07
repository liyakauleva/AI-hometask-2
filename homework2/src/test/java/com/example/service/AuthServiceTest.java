package com.example.service;

import com.example.entity.Authentication;
import com.example.repository.AuthenticationRepository;
import com.example.security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class AuthServiceTest {
    @Mock AuthenticationRepository authRepository;
    @Mock PasswordEncoder passwordEncoder;
    @Mock JwtUtil jwtUtil;
    @InjectMocks AuthService authService;

    @BeforeEach
    void setUp() { MockitoAnnotations.openMocks(this); }

    @Test
    void register_success() {
        when(authRepository.findByEmail("test@example.com")).thenReturn(Optional.empty());
        when(passwordEncoder.encode("password")).thenReturn("hashed");
        when(authRepository.save(any(Authentication.class))).thenAnswer(i -> i.getArgument(0));
        assertDoesNotThrow(() -> authService.register("Test", "test@example.com", "password"));
    }

    @Test
    void register_duplicateEmail_throws() {
        when(authRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new Authentication()));
        assertThrows(RuntimeException.class, () -> authService.register("Test", "test@example.com", "password"));
    }

    @Test
    void login_success() {
        Authentication auth = new Authentication();
        auth.setEmail("test@example.com");
        auth.setPasswordHash("hashed");
        when(authRepository.findByEmail("test@example.com")).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches("password", "hashed")).thenReturn(true);
        when(jwtUtil.generateToken(anyMap(), eq("test@example.com"))).thenReturn("token");
        String token = authService.login("test@example.com", "password");
        assertEquals("token", token);
    }

    @Test
    void login_invalidPassword_throws() {
        Authentication auth = new Authentication();
        auth.setEmail("test@example.com");
        auth.setPasswordHash("hashed");
        when(authRepository.findByEmail("test@example.com")).thenReturn(Optional.of(auth));
        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);
        assertThrows(RuntimeException.class, () -> authService.login("test@example.com", "wrong"));
    }

    @Test
    void login_notFound_throws() {
        when(authRepository.findByEmail("notfound@example.com")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> authService.login("notfound@example.com", "password"));
    }
} 