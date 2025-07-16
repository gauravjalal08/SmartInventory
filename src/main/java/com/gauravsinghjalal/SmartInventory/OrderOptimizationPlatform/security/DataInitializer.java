package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.security;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.Role;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.User;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository.RoleRepositry;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository.UserRepositry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createDefaultAdmin(UserRepositry userRepository,
                                                RoleRepositry roleRepository,
                                                PasswordEncoder passwordEncoder) {
        return args -> {

            // ✅ Step 1: Create ROLE_ADMIN only if not present
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_ADMIN");
                        return roleRepository.save(role);
                    });

            /// ✅ Step 2: Create admin user only if not present
            if (userRepository.findByUsername("admin").isEmpty()) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRoles(Set.of(adminRole));
                userRepository.save(admin);
                System.out.println("✅ Admin created: username=admin | password=admin123");
            } else {
                System.out.println("ℹ️ Admin already exists.");
            }
        };
    }
}
