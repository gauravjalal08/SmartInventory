package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationRequest {
    private String username;
    private String password;

}

