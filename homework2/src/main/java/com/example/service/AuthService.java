package com.example.service;

import com.example.entity.Authentication;
import com.example.repository.AuthenticationRepository;
import com.example.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public void register(String name, String email, String password) {
        if (authRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered");
        }
        Authentication auth = new Authentication();
        auth.setName(name);
        auth.setEmail(email);
        auth.setPasswordHash(passwordEncoder.encode(password));
        authRepository.save(auth);
    }

    public String login(String email, String password) {
        Authentication auth = authRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
        if (!passwordEncoder.matches(password, auth.getPasswordHash())) {
            throw new RuntimeException("Invalid credentials");
        }
        return jwtUtil.generateToken(
            java.util.Map.of("name", auth.getName(), "email", auth.getEmail()),
            auth.getEmail()
        );
    }
} 