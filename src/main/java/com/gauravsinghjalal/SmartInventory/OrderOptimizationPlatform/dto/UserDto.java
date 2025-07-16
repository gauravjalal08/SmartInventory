package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String Username;
    private String password;
    private Set<String> roles;

}
