package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class InventoryDto {
    private  Long id;
    private String name;
    private String sku;
    private int quantity;
    private int reorderThreshold;

}
