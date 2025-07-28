package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventory_items")
public class InventoryItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String name;
    private String sku;
    private int quantity;
    private int reorderThreshold;
    private LocalDateTime lastUpdated;
    private String supplier;
    private String orderStatus;

}
