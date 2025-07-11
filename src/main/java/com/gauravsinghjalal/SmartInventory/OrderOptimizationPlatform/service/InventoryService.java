package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.InventoryDto;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.entity.InventoryItem;

import java.util.List;

public interface InventoryService {
    InventoryDto createItem(InventoryDto dto);
    List<InventoryDto> getAllItems();
    InventoryDto updateItem(Long id, InventoryDto dto);
    void deleteItem(Long id);
    List<InventoryDto> getLowStockItems();


}
