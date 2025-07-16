package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.UserDto;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.Role;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.User;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository.RoleRepositry;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository.UserRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private RoleRepositry roleRepositry;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(UserDto userDto){
        User user=new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        // Assign default role: "ROLE_USER"
        Role defaultRole = roleRepositry.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Default role not found"));

        user.setRoles(Set.of(defaultRole));

        userRepositry.save(user);
    }

}
