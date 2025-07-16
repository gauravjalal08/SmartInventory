package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.controller;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.AuthRequest;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.AuthResponse;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}
