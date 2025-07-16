package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.AuthRequest;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.AuthResponse;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.Role;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.User;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.jwt.JwtUtils;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthService {

    @Autowired
    private UserRepositry userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        // 🔍 Debug prints
        System.out.println("Raw password: " + request.getPassword());
        System.out.println("Encoded password in DB: " + user.getPassword());
        System.out.println("Matches: " + passwordEncoder.matches(request.getPassword(), user.getPassword()));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String username = user.getUsername();
        List<String> roles= user.getRoles()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());

        String token = jwtUtils.generateToken(username,roles);
        return new AuthResponse(token);
    }
}
