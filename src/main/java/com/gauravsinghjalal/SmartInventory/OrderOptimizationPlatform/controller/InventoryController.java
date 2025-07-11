package com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.controller;

import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.dto.InventoryDto;
import com.gauravsinghjalal.SmartInventory.OrderOptimizationPlatform.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController  {
    @Autowired
    private InventoryService  inventoryService;

    @PostMapping
    public InventoryDto createItem(@RequestBody InventoryDto dto) {
        return inventoryService.createItem(dto);


    }

    @GetMapping
    public List<InventoryDto> getAllItems() {
        return inventoryService.getAllItems();
    }

    @PutMapping("/{id}")
    public InventoryDto updateItem(@PathVariable Long id, @RequestBody InventoryDto dto) {
        return inventoryService.updateItem(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id) {
        inventoryService.deleteItem(id);
    }

    @GetMapping("/low-stock")
    public List<InventoryDto> getLowStockItems() {
        return inventoryService.getLowStockItems();
    }
}

