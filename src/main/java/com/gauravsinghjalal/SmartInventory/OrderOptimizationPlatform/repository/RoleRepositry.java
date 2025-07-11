package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.repository;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepositry extends JpaRepository<Role,Long>{
    Optional<Role> findByName(String name);
}
